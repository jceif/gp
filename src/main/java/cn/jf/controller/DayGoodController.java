package cn.jf.controller;

import cn.jf.model.company.Company;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.daygood.DayGoodVo1;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.service.company.CompanyService;
import cn.jf.service.daygood.DayGoodService;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/data/")
public class DayGoodController {

  @Autowired
  private DayGoodService dayGoodService;
  @Autowired
  private DayValueService dayValueService;
  @Autowired
  private CompanyService companyService;

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
  List<String> preDates = new ArrayList<String>();
  List<String> pre1Dates = new ArrayList<String>();
  List<String> pre2Dates = new ArrayList<String>();

  @RequestMapping("/")
  public String index(HttpServletRequest request, String date, String time, String price, String mainMoney, String rate,
      String preDate1, String preDate2, String type, String marketWorth1, String marketWorth2) {
    if (request.getSession().getAttribute("user") == null) {
      return "redirect:/login";
    }
    //获取日期属性
    getFormatDates(request);
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isEmpty(time)) {
      time = "1030";
    }
    map.put("time", time);
    if (StringUtils.isEmpty(time)) {
      map.put("preTime1", "1000");
    } else {
      if (time.equals("1300")) {
        map.put("preTime1", "1130");
      } else {
        if (time.contains("30")) {
          map.put("preTime1", Integer.parseInt(time) - 30);
        } else {
          map.put("preTime1", Integer.parseInt(time) - 70);
        }
      }
    }
    if (!StringUtils.isEmpty(time) && Integer.parseInt(time) >= 1030) {
      if (time.equals("1300")) {
        map.put("preTime2", "1100");
      } else if (time.equals("1330")) {
        map.put("preTime2", "1130");
      } else {
        map.put("preTime2", Integer.parseInt(time) - 100);
      }
    } else {
      map.put("preTime2", map.get("preTime1"));
    }
    if (StringUtils.isEmpty(price)) {
      price = "100.24";
    }
    map.put("price", price);
    if (StringUtils.isEmpty(mainMoney)) {
      mainMoney = "3500";
    }
    map.put("mainMoney", mainMoney);    //主要资金
    if (StringUtils.isEmpty(rate)) {
      rate = "11";
    }
    map.put("rate", rate);

    if (StringUtils.isEmpty(date)) {
      date = preDates.get(0);
    }
    if (StringUtils.isEmpty(preDate1)) {
      preDate1 = pre1Dates.get(0);
    }
    if (StringUtils.isEmpty(preDate2)) {
      preDate2 = pre2Dates.get(0);
    }
    map.put("date", date);
    map.put("preDayDate1", preDate1);
    map.put("preDayDate2", preDate2);
    map.put("type", type);
    map.put("marketWorth1", marketWorth1);
    map.put("marketWorth2", marketWorth2);

    List<DayGoodVo> dayGoods = dayGoodService.findDayGoodNowQuery(map);
    for (DayGoodVo dayGood : dayGoods) {
      Integer count=dayValueService.findCountByCompanyCode(dayGood.getCompanyCode(),dayGood.getDate());
      if(count!=null && count<15){
        dayGood.setCompanyName(dayGood.getCompanyName()+"-n");
      }
    }
    request.setAttribute("dayGoods", dayGoods);
    request.setAttribute("date", date);
    request.setAttribute("time", time);
    request.setAttribute("price", price);
    request.setAttribute("mainMoney", mainMoney);
    request.setAttribute("rate", rate);
    request.setAttribute("preDate1", preDate1);
    request.setAttribute("preDate2", preDate2);
    request.setAttribute("type", type);
    request.setAttribute("marketWorth1", marketWorth1);
    request.setAttribute("marketWorth2", marketWorth2);
    return "index";
  }





  @RequestMapping("/chart")
  public String echart(HttpServletRequest request, String companyCode, String date) {
    Company company= companyService.findCompanyByCode(companyCode);
    Map<String, Object> map = new HashMap<String, Object>();
    double maxPrice=0;
    double minPrice=0;
    map.put("date", date);
    map.put("companyCode", companyCode);
    List<DayGood> dayGoods = dayGoodService.findDayGoodQuery(map);
    List<String> times = new ArrayList<String>();
    List<Double> prices = new ArrayList<Double>();
    List<Double> rates = new ArrayList<Double>();
    List<Double> moneys = new ArrayList<Double>();
    double nowPrice = 0;
    if (dayGoods != null && dayGoods.size() > 0) {
      for (DayGood dayGood : dayGoods) {
        minPrice=dayGood.getPrice();
        times.add(dayGood.getTime());
        prices.add(dayGood.getPrice());
        rates.add(dayGood.getRate());
        moneys.add(dayGood.getMainMoney());
        if(minPrice>dayGood.getPrice()){
          minPrice=dayGood.getPrice();
        }
        if(maxPrice<dayGood.getPrice()){
          maxPrice=dayGood.getPrice();
        }
      }
    }
    request.setAttribute("times", JSONUtils.toJSONString(times));
    request.setAttribute("rates", JSONUtils.toJSONString(rates));
    request.setAttribute("prices", JSONUtils.toJSONString(prices));
    request.setAttribute("moneys", JSONUtils.toJSONString(moneys));

    if(company!=null){
      request.setAttribute("companyName", company.getName());
      request.setAttribute("companyCode", company.getCode());
    }
    request.setAttribute("date", date);
    request.setAttribute("minPrice", BigDecimal.valueOf(minPrice).subtract(BigDecimal.valueOf(1)));
    request.setAttribute("maxPrice", BigDecimal.valueOf(maxPrice).add(BigDecimal.valueOf(1)));
    return "chart";

  }



  @RequestMapping("/topList")
  public String findTotalMoneyTopList(HttpServletRequest request,String time,String rate,String inflow) {
    if (request.getSession().getAttribute("user") == null) {
      return "redirect:/login";
    }
    if(StringUtils.isEmpty(time)){
      time="1000";
    }
    if(StringUtils.isEmpty(rate)){
      rate="5";
    }
    if(StringUtils.isEmpty(inflow)){
      inflow="6000";
    }

    List<DayGoodVo1> dayGoodVo1s = dayGoodService.findDGLastRateByTimeAndInflow(time,Float.parseFloat(rate),Float.parseFloat(inflow));
    List<Integer> dates=dayValueService.findDays();
    DayValue nextDay = null;
    BigDecimal oneRateSum = BigDecimal.valueOf(0);
    BigDecimal twoRateSum = BigDecimal.valueOf(0);
    DayGoodVo1 dayGoodVo1=null;
    for (int i = 1; i < dayGoodVo1s.size(); i++) {
      dayGoodVo1=dayGoodVo1s.get(i);
      int nextDayValue=dates.get(dates.indexOf(dayGoodVo1.getDate())-1);
      nextDay = dayValueService.findDayValueByIdAndDate(dayGoodVo1.getCompanyCode(), nextDayValue);
      oneRateSum=oneRateSum.add(BigDecimal.valueOf(dayGoodVo1.getLastRate()).subtract(BigDecimal.valueOf(dayGoodVo1.getPreRate())));
      twoRateSum=twoRateSum.add(BigDecimal.valueOf(dayGoodVo1.getLastRate()).subtract(BigDecimal.valueOf(dayGoodVo1.getPreRate())));
      if (nextDay != null && nextDay.getId() > 0) {
        dayGoodVo1s.get(i).setTwoEndPrice(nextDay.getEndPrice());
        dayGoodVo1s.get(i).setTwoStartPrice(nextDay.getStartPrice());
        dayGoodVo1s.get(i).setTwoRate(nextDay.getRate());
        //如果跌幅超过-2必须卖掉
        if(nextDay.getRate()<-2) {
          twoRateSum = twoRateSum.add(BigDecimal.valueOf(-2.5));
        }else{
          twoRateSum = twoRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
        }
      }
    }
    getFormatDates(request);
    request.setAttribute("dayGoodVo1s", dayGoodVo1s);
    request.setAttribute("oneRateSum", oneRateSum);
    request.setAttribute("twoRateSum", twoRateSum);
    return "topGoodList";
  }


  /*日期函数*/
  private void getFormatDates(HttpServletRequest request) {
    preDates = new ArrayList<String>();
    pre1Dates = new ArrayList<String>();
    pre2Dates = new ArrayList<String>();
    Calendar calendar = Calendar.getInstance();
    for (int i = 0; i < 31; i++) {
      if (calendar.get(Calendar.DAY_OF_WEEK) != 7 && calendar.get(Calendar.DAY_OF_WEEK) != 1) {
        preDates.add(simpleDateFormat.format(calendar.getTime()));
        if (i > 0 && (preDates.size() - pre1Dates.size()) > 1) {
          pre1Dates.add(simpleDateFormat.format(calendar.getTime()));
        }
        if (i > 1 && (pre1Dates.size() - pre2Dates.size()) > 1) {
          pre2Dates.add(simpleDateFormat.format(calendar.getTime()));
        }
      }
      calendar.add(Calendar.DATE, -1);
    }
    request.setAttribute("formatDates", preDates);
    request.setAttribute("formatDates1", pre1Dates);
    request.setAttribute("formatDates2", pre2Dates);
  }




}
