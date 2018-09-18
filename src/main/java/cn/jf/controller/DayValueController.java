package cn.jf.controller;

import cn.jf.common.PageUtil;
import cn.jf.model.company.Company;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVO;
import cn.jf.service.company.CompanyService;
import cn.jf.service.daygood.DayGoodService;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.mysql.cj.xdevapi.JsonParser;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "dayIndex";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, String companyCode, String count) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
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
        return "dayValue";
    }


    /**
     * 72天内的平均值
     */
    @RequestMapping("/info")
    @ResponseBody
    public String dayValueInfo(HttpServletRequest request, String companyCode) {
        List<DayValue> dayValues = dayValueService.dayValueTop5(companyCode);
        Double avgValue = dayValueService.dayValueAverage(companyCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("avg", avgValue);
        map.put("topValue", dayValues);
        String json = JSONObject.valueToString(map);
        return json;
    }


    @RequestMapping("/topList")
    public String findTotalMoneyTopList(HttpServletRequest request,String dateStart,String dateEnd,String day) {

        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        if(StringUtils.isEmpty(dateStart)){
            dateStart="20180503";
        }
        if(StringUtils.isEmpty(dateEnd)){
            dateEnd=simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        if(StringUtils.isEmpty(day)){
            day="15";
        }
        List<DayValueVO> dayValueList = new ArrayList<DayValueVO>();
        List<DayValue> dayValues = dayValueService.findTotalMoneyTopList(Integer.parseInt(dateStart), Integer.parseInt(dateEnd));
        DayValueVO dayValueVO = null;
        DayValue preDay = null;
        DayValue nextDay = null;
        DayValue threeDay = null;
        BigDecimal rateAllSum = BigDecimal.valueOf(0);
        BigDecimal rateAllSumThree = BigDecimal.valueOf(0);
        for (int i = 1; i < dayValues.size(); i++) {
            dayValueVO = new DayValueVO();
            preDay = dayValues.get(i);
            if (preDay.getTotalMoney() == 0) {
                continue;
            }
            nextDay = dayValueService.findDayValueByIdAndDate(preDay.getCompanyCode(), dayValues.get(i - 1).getDate());
            if (nextDay != null && nextDay.getId() > 0) {
                dayValueVO.setCompanyCode(preDay.getCompanyCode());
                dayValueVO.setDate(preDay.getDate());
                dayValueVO.setId(preDay.getId());
                dayValueVO.setNextEndPrice(nextDay.getEndPrice());
                dayValueVO.setNextMaxPrice(nextDay.getMaxPrice());
                dayValueVO.setNextMinPrice(nextDay.getMinPrice());
                dayValueVO.setNextStartPrice(nextDay.getStartPrice());
                dayValueVO.setNextRate(nextDay.getRate());
                dayValueVO.setNextTotalMoney(nextDay.getTotalMoney());
                dayValueVO.setPreEndPrice(preDay.getEndPrice());
                dayValueVO.setPreMaxPrice(preDay.getMaxPrice());
                dayValueVO.setPreMinPrice(preDay.getMinPrice());
                dayValueVO.setPreStartPrice(preDay.getStartPrice());
                dayValueVO.setPreRate(preDay.getRate());
                dayValueVO.setPreTotalMoney(preDay.getTotalMoney());
                if(nextDay.getRate()<-3) {
                    rateAllSum = rateAllSum.add(BigDecimal.valueOf(-3.5));
                    rateAllSumThree = rateAllSumThree.add(BigDecimal.valueOf(-3.5));
                }else{
                    rateAllSum = rateAllSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    rateAllSumThree = rateAllSumThree.add(BigDecimal.valueOf(nextDay.getRate()));
                }
                dayValueVO.setIsNew(0);
                if(i>1) {
                    threeDay = dayValueService.findDayValueByIdAndDate(preDay.getCompanyCode(), dayValues.get(i - 2).getDate());
                    if(threeDay!=null && threeDay.getId()>0) {
                        if (nextDay.getRate() > 2) {
                            if(threeDay.getRate()<-3) {
                                rateAllSumThree = rateAllSumThree.add(BigDecimal.valueOf(-3.5));
                            }
                            else{
                                rateAllSumThree = rateAllSumThree.add(BigDecimal.valueOf(threeDay.getRate()));
                            }
                        }
                        dayValueVO.setThreeEndPrice(threeDay.getEndPrice());
                        dayValueVO.setThreeMaxPrice(threeDay.getMaxPrice());
                        dayValueVO.setThreeRate(threeDay.getRate());
                    }
                }
                dayValueList.add(dayValueVO);
            }
        }
        getFormatDates(request);
        request.setAttribute("dayValues", dayValueList);
        request.setAttribute("rateAllSum", rateAllSum);
        request.setAttribute("rateAllSumThree", rateAllSumThree);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("day", day);
        return "topList";
    }



    /*日期函数*/
    private void getFormatDates(HttpServletRequest request) {
        List<String> dateStarts = new ArrayList<String>();
        List<String> dateEnds = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 40; i++) {
            if (calendar.get(Calendar.DAY_OF_WEEK) != 7 && calendar.get(Calendar.DAY_OF_WEEK) != 1) {
                dateStarts.add(simpleDateFormat.format(calendar.getTime()));
                if (i > 0 && (dateStarts.size() - dateEnds.size()) > 1) {
                    dateEnds.add(simpleDateFormat.format(calendar.getTime()));
                }
                calendar.add(Calendar.DATE, -7);
            }else{
                calendar.add(Calendar.DATE, -1);
            }
        }
        request.setAttribute("dateStarts", dateStarts);
        request.setAttribute("dateEnds", dateEnds);
    }

}
