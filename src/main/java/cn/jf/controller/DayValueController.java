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
     *
     * @param companyCode
     */
    @RequestMapping("/index")
    public String dayValueTop5(HttpServletRequest request, String companyCode, String count) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        Company company = companyService.findCompanyByCode(companyCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyCode", companyCode);
        PageUtil<DayValue> pageUtil = dayValueService.findDayValueQueryPage(map, "0", count);
        request.setAttribute("company", company);
        List<DayValue> dayValues = pageUtil.getRecords();
        request.setAttribute("dayValues", dayValues);
        return "dayValue";
    }


    /**
     * 72天内的平均值
     *
     * @param companyCode
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
