package cn.jf.controller;

import cn.jf.common.PageUtil;
import cn.jf.model.company.Company;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.service.company.CompanyService;
import cn.jf.service.daygood.DayGoodService;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.mysql.cj.xdevapi.JsonParser;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 72天内的最高值
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        dayValueService.dayValueUpList();
        List<DayValue> dayValues=dayValueService.dayValueUpList();
        request.setAttribute("dayValues",dayValues);
        return "dayIndex";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, String companyCode, String count){
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


}
