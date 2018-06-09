package cn.jf.controller;

import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.service.daygood.DayGoodService;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.mysql.cj.xdevapi.JsonParser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  /**
   * 72天内的最高值
   * @param companyCode
   */
  @RequestMapping("/top")
  @ResponseBody
  public void dayValueTop5(HttpServletRequest request,String companyCode){
    List<DayValue> dayValues =dayValueService.dayValueTop5(companyCode);
  }

  /**
   * 72天内的平均值
   * @param companyCode
   */
  @RequestMapping("/average")
  @ResponseBody
  public void dayValueAverage(HttpServletRequest request,String companyCode){
    double avgValue  =dayValueService.dayValueAverage(companyCode);
  }

  /**
   * 72天内的平均值
   * @param companyCode
   */
  @RequestMapping("/info")
  @ResponseBody
  public String dayValueInfo(HttpServletRequest request,String companyCode){
    List<DayValue> dayValues =dayValueService.dayValueTop5(companyCode);
    Double avgValue  =dayValueService.dayValueAverage(companyCode);
    Map<String,Object> map=new HashMap<String, Object>();
    map.put("avg",avgValue);
    map.put("topValue",dayValues);
    String json=JSONObject.valueToString(map);
    return json;
  }



}
