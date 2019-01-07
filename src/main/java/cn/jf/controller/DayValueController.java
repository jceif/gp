package cn.jf.controller;

import cn.jf.common.FormatDate;
import cn.jf.common.PageUtil;
import cn.jf.model.company.Company;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVo1;
import cn.jf.service.company.CompanyService;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/day/")
public class DayValueController {

    @Autowired
    private DayValueService dayValueService;

    @Autowired
    private CompanyService companyService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 统计最近kdj macd diff dea 连续三天上涨的值
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        dayValueService.dayValueUpList();
        List<DayValue> dayValues = dayValueService.dayValueUpList();
        request.setAttribute("dayValues", dayValues);
        return "dayvalue/dayIndex";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, String companyCode, String count) {
        Company company = companyService.findCompanyByCode(companyCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyCode", companyCode);
        PageUtil<DayValue> pageUtil = dayValueService.findDayValueQueryPage(map, "0", count);
        request.setAttribute("company", company);
        List<DayValue> dayValues = pageUtil.getRecords();
        request.setAttribute("dayValues", pageUtil.getRecords());
        List<Integer> dates = new ArrayList<Integer>();

        List<Double> rates = new ArrayList<Double>();
        List<Double> inflows = new ArrayList<Double>();
        List<Float> diffs = new ArrayList<Float>();
        List<Float> deas = new ArrayList<Float>();
        List<Float> macds = new ArrayList<Float>();

        List<Float> ks = new ArrayList<Float>();
        List<Float> ds = new ArrayList<Float>();
        List<Float> js = new ArrayList<Float>();

        if (dayValues != null && dayValues.size() > 0) {
            DayValue dayValue = null;
            for (int i = dayValues.size() - 1; i > -1; i--) {
                dayValue = dayValues.get(i);
                dates.add(dayValue.getDate());
                rates.add(dayValue.getRate());
                inflows.add(dayValue.getTotalMoney());
                diffs.add(dayValue.getDiff());
                deas.add(dayValue.getDea());
                macds.add(dayValue.getMacd());
                ks.add(dayValue.getK());
                ds.add(dayValue.getD());
                js.add(dayValue.getJ());
            }
        }

        request.setAttribute("dates", JSONUtils.toJSONString(dates));
        request.setAttribute("rates", JSONUtils.toJSONString(rates));
        request.setAttribute("inflows", JSONUtils.toJSONString(inflows));
        request.setAttribute("diffs", JSONUtils.toJSONString(diffs));
        request.setAttribute("deas", JSONUtils.toJSONString(deas));
        request.setAttribute("macds", JSONUtils.toJSONString(macds));
        request.setAttribute("ks", JSONUtils.toJSONString(ks));
        request.setAttribute("ds", JSONUtils.toJSONString(ds));
        request.setAttribute("js", JSONUtils.toJSONString(js));
        return "dayvalue/dayDetail";
    }

    @RequestMapping("/topRateList")
    public String findDayValueZt(HttpServletRequest request, String rate,String totalMoney,String dateStart,String dateEnd) {
        if (StringUtils.isEmpty(rate)) {
            rate = "9";
        }
        if (StringUtils.isEmpty(totalMoney)) {
            totalMoney = "1000";
        }
        if (StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        List<Integer> dates = dayValueService.findDays();
        List<DayValue> dayValues = dayValueService.findDayValueZt(Float.parseFloat(rate), Float.parseFloat(totalMoney), Integer.parseInt(dateStart), Integer.parseInt(dateEnd));
        Map<String, List<DayValue>> listMap = new LinkedHashMap<String, List<DayValue>>();
        BigDecimal dayRateSum = BigDecimal.valueOf(0);//一天数据的涨幅
        BigDecimal daysRateSum = BigDecimal.valueOf(0);//总数据涨幅
        BigDecimal rateTest = BigDecimal.valueOf(0);//测试
        List<DayValue> dayValueList = new ArrayList<DayValue>();
        int currentDate = 0;
        for (int i = 0; i < dayValues.size(); i++) {
            if (currentDate == 0) {
                currentDate = dayValues.get(i).getDate();
            }
            DayValue dayValue = dayValues.get(i);
            //创业板
            if (dayValue.getCompanyCode().startsWith("300") || dates.indexOf(dayValue.getDate()) == 0) {
                continue;
            }
            if (currentDate != dayValues.get(i).getDate()) {
                if (dayValueList.size() > 0) {
                    listMap.put(currentDate + "_" + (dayRateSum
                        .divide(BigDecimal.valueOf(dayValueList.size()), 2, BigDecimal.ROUND_HALF_EVEN)), dayValueList);
                    daysRateSum = daysRateSum
                        .add(dayRateSum.divide(BigDecimal.valueOf(dayValueList.size()), 2, BigDecimal.ROUND_HALF_EVEN));
                    // currentDate = dayValues.get(i).getDate();
                }
                dayRateSum = BigDecimal.valueOf(0);
                dayValueList = new ArrayList<DayValue>();
                currentDate = dayValue.getDate();
            }
            DayValue preDay = dayValueService.findDayValueByIdAndDate(dayValue.getCompanyCode(), dates.get(dates.indexOf(dayValue.getDate()) + 1));
            DayValue nextDay = dayValueService.findDayValueByIdAndDate(dayValue.getCompanyCode(), dates.get(dates.indexOf(dayValue.getDate()) - 1));
            if (dates.indexOf(dayValue.getDate()) > 1) {
                DayValue threeDay = dayValueService.findDayValueByIdAndDate(dayValue.getCompanyCode(), dates.get(dates.indexOf(dayValue.getDate()) - 2));
                dayValue.setThreeRate(threeDay == null ? 0.0 : threeDay.getRate());
            }
            if (preDay == null) {
                System.out.println("----" + dayValue.getCompanyCode() + "-" + dayValue.getDate());
                continue;
            }
            dayValue.setPreRate(preDay == null ? 0.00 : preDay.getRate());
            dayValue.setNextRate(nextDay == null ? 0.00 : nextDay.getRate());
            //前一天的增长比率小于-4
            if (preDay.getRate() < -4) {
                continue;
            }
            if (preDay != null && preDay.getMacd() != 0 && preDay.getDiff() != 0 && preDay.getDea() != 0) {
                dayValue.setPreK(preDay.getK());
                dayValue.setPreD(preDay.getD());
                dayValue.setPreJ(preDay.getJ());
            }
            /*if(  dayValue.getPreJ()<1   && dayValue.getPreK()>0 ){*/
            if (dayValue.getPreJ() < 2 && dayValue.getPreD() > 22 && dayValue.getPreD() < 50 && dayValue.getPreK() > 0) {
                rateTest = rateTest.add(BigDecimal.valueOf(dayValue.getNextRate()));
            } else {
                continue;
            }
            if (nextDay != null) {
                dayRateSum = dayRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
            }
            dayValueList.add(dayValue);
        }
        FormatDate.getFormatDates_month(request);
        request.setAttribute("rate", rate);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("daysRateSum", daysRateSum);
        request.setAttribute("listMap", listMap);
        return "dayvalue/topRateList";
    }


    @RequestMapping("/topInflowList")
    public String findByInflowDays(HttpServletRequest request,String dateStart,String dateEnd,String dura){
        int start = 0;
        int current = 0;
        int end = 0;
        int dur=4;//统计多少天的流入
        if(dura!=null && !StringUtils.isEmpty(dura)){
            dur=Integer.parseInt(dura);
        }
        List<Integer> dates = dayValueService.findDays();
        BigDecimal rateSum=BigDecimal.ZERO;
        List<DayValueVo1> dayValueVo1List=new ArrayList<DayValueVo1>();
        if (dateStart==null || StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (dateEnd== null || StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        int iStart = dates.indexOf(Integer.parseInt(dateStart));
        int t=0;
        while (iStart<0){
            iStart = dates.indexOf(Integer.parseInt(dateStart)+t);
            t++;
        }
        t=0;
        int iEnd = dates.indexOf(Integer.parseInt(dateEnd));//
        while (iEnd<0){
            iEnd = dates.indexOf(Integer.parseInt(dateEnd)-t);
            t++;
        }
        for (int i = iEnd; i < iStart; i++) {
            end = dates.get(i);
            if(i>0) {
                current = dates.get(i - 1);
            }else{
                current=0;
            }
            start = dates.get(i + dur);
            List<DayValueVo1> list = dayValueService.findByInflowDays(start, end, current);
            if(list!=null && list.size()>0){
                dayValueVo1List.add(list.get(0));
                rateSum=rateSum.add(BigDecimal.valueOf(list.get(0).getRate()));
            }
        }
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("rateSum", rateSum);
        request.setAttribute("dura", dura);
        request.setAttribute("list", dayValueVo1List);
        FormatDate.getFormatDates_month(request);
        return "dayvalue/topInflow";
    }

}
