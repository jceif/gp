package cn.jf.controller;

import cn.jf.common.FormatDate;
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

    @RequestMapping("/")
    public String index(HttpServletRequest request, String date, String time, String price, String mainMoney, String rate,
                        String preDate1, String preDate2, String type, String marketWorth1, String marketWorth2) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        //获取日期属性
        FormatDate.getFormatDates_Day(request);
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
            date = ((List<String>) request.getAttribute("formatDates")).get(0);
        }
        if (StringUtils.isEmpty(preDate1)) {
            preDate1 = ((List<String>) request.getAttribute("formatDates1")).get(0);
        }
        if (StringUtils.isEmpty(preDate2)) {
            preDate2 = ((List<String>) request.getAttribute("formatDates2")).get(0);
        }
        map.put("date", date);
        map.put("preDayDate1", preDate1);
        map.put("preDayDate2", preDate2);
        map.put("type", type);
        map.put("marketWorth1", marketWorth1);
        map.put("marketWorth2", marketWorth2);

        List<DayGoodVo> dayGoods = dayGoodService.findDayGoodNowQuery(map);
        for (DayGoodVo dayGood : dayGoods) {
            Integer count = dayValueService.findCountByCompanyCode(dayGood.getCompanyCode(), dayGood.getDate());
            if (count != null && count < 15) {
                dayGood.setCompanyName(dayGood.getCompanyName() + "-n");
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
        Company company = companyService.findCompanyByCode(companyCode);
        Map<String, Object> map = new HashMap<String, Object>();
        double maxPrice = 0;
        double minPrice = 0;
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
                minPrice = dayGood.getPrice();
                times.add(dayGood.getTime());
                prices.add(dayGood.getPrice());
                rates.add(dayGood.getRate());
                moneys.add(dayGood.getMainMoney());
                if (minPrice > dayGood.getPrice()) {
                    minPrice = dayGood.getPrice();
                }
                if (maxPrice < dayGood.getPrice()) {
                    maxPrice = dayGood.getPrice();
                }
            }
        }
        request.setAttribute("times", JSONUtils.toJSONString(times));
        request.setAttribute("rates", JSONUtils.toJSONString(rates));
        request.setAttribute("prices", JSONUtils.toJSONString(prices));
        request.setAttribute("moneys", JSONUtils.toJSONString(moneys));

        if (company != null) {
            request.setAttribute("companyName", company.getName());
            request.setAttribute("companyCode", company.getCode());
        }
        request.setAttribute("date", date);
        request.setAttribute("minPrice", BigDecimal.valueOf(minPrice).subtract(BigDecimal.valueOf(1)));
        request.setAttribute("maxPrice", BigDecimal.valueOf(maxPrice).add(BigDecimal.valueOf(1)));
        return "chart";

    }


    @RequestMapping("/topList")
    public String findTotalMoneyTopList(HttpServletRequest request, String time, String rate, String inflow,
                                        String dateStart, String dateEnd) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (StringUtils.isEmpty(time)) {
            time = "1000";
        }
        if (StringUtils.isEmpty(rate)) {
            rate = "5";
        }
        if (StringUtils.isEmpty(inflow)) {
            inflow = "6000";
        }
        if (StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        List<DayGoodVo1> dayGoodVo1s = dayGoodService
                .findDGLastRateByTimeAndInflow(time, Float.parseFloat(rate), Float.parseFloat(inflow), Integer.parseInt(dateStart), Integer.parseInt(dateEnd));
        if (dayGoodVo1s != null && dayGoodVo1s.size() > 0) {
            Collections.sort(dayGoodVo1s, new Comparator<DayGoodVo1>() {
                @Override
                public int compare(DayGoodVo1 o1, DayGoodVo1 o2) {
                    if (o1.getDate() > o2.getDate()) {
                        return -1;
                    } else if (o1.getDate() == o2.getDate()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        List<Integer> dates = dayValueService.findDays();
        DayValue nextDay = null;
        BigDecimal twoRateSum = BigDecimal.valueOf(0);
        DayGoodVo1 dayGoodVo1 = null;
        double sellMinPrice = -1.5;//最小卖出价格
        BigDecimal ztRateSum = BigDecimal.valueOf(0);//非涨停收益率
        for (int i = 1; i < dayGoodVo1s.size(); i++) {
            dayGoodVo1 = dayGoodVo1s.get(i);
            if (dates.indexOf(dayGoodVo1.getDate()) == 0) {
                continue;
            }
            int nextDayValue = dates.get(dates.indexOf(dayGoodVo1.getDate()) - 1);
            nextDay = dayValueService.findDayValueByIdAndDate(dayGoodVo1.getCompanyCode(), nextDayValue);
            twoRateSum = twoRateSum.add(BigDecimal.valueOf(dayGoodVo1.getLastRate()).subtract(BigDecimal.valueOf(dayGoodVo1.getPreRate())));
            ztRateSum = ztRateSum.add(BigDecimal.valueOf(dayGoodVo1.getLastRate()).subtract(BigDecimal.valueOf(dayGoodVo1.getPreRate())));
            if (nextDay != null && nextDay.getId() > 0) {
                dayGoodVo1s.get(i).setTwoEndPrice(nextDay.getEndPrice());
                dayGoodVo1s.get(i).setTwoStartPrice(nextDay.getStartPrice());
                dayGoodVo1s.get(i).setTwoRate(nextDay.getRate());
                //如果跌幅超过-2必须卖掉
                if (nextDay.getRate() < sellMinPrice) {
                    twoRateSum = twoRateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                } else {
                    twoRateSum = twoRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                }
                if (dayGoodVo1.getPreRate() < 9.5) {
                    if (nextDay.getRate() < sellMinPrice) {
                        ztRateSum = ztRateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                    } else {
                        ztRateSum = ztRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    }
                }
            }
        }
        FormatDate.getFormatDates(request);
        request.setAttribute("time", time);
        request.setAttribute("rate", rate);
        request.setAttribute("inflow", inflow);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("dayGoodVo1s", dayGoodVo1s);
        request.setAttribute("twoRateSum", twoRateSum);
        request.setAttribute("ztRateSum", ztRateSum);

        return "topGoodList";
    }


    @RequestMapping("/topTimeList")
    public String findTopOneByTime(HttpServletRequest request, String time, String dateStart, String dateEnd) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (StringUtils.isEmpty(time)) {
            time = "1130";
        }
        if (StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        List<DayGood> dayGoods = dayGoodService.findTopOneByTime(time, Integer.parseInt(dateStart), Integer.parseInt(dateEnd));
        List<Integer> dates = dayValueService.findDays();
        DayValue currentDay = null;
        DayValue nextDay = null;
        DayValue threeDay = null;
        DayGoodVo1 dayGoodVo1 = null;
        DayGood dayGood = null;
        double sellMinPrice = -1.5;//最小卖出价格
        BigDecimal fztRateSum = BigDecimal.valueOf(0);//非涨停收益率
        BigDecimal rateSum = BigDecimal.valueOf(0);//所有收益率
        List<DayGoodVo1> dayGoodVo1s = new ArrayList<DayGoodVo1>();
        for (int i = 1; i < dayGoods.size(); i++) {
            dayGood = dayGoods.get(i);
            if (dates.indexOf(dayGood.getDate()) == 0) {
                continue;
            }
            currentDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dayGood.getDate());
            nextDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dates.get(dates.indexOf(dayGood.getDate()) - 1));
            fztRateSum = fztRateSum.add(BigDecimal.valueOf(currentDay.getEndPrice()).subtract(BigDecimal.valueOf(dayGood.getPrice())));
            rateSum = rateSum.add(BigDecimal.valueOf(currentDay.getEndPrice()).subtract(BigDecimal.valueOf(dayGood.getPrice())));
            dayGoodVo1 = new DayGoodVo1();
            dayGoodVo1.setPreTime(Integer.parseInt(time));
            dayGoodVo1.setPreRate(dayGood.getRate());
            dayGoodVo1.setPrePrice(dayGood.getPrice());
            dayGoodVo1.setPreInflow(dayGood.getMainMoney());
            dayGoodVo1.setCompanyCode(dayGood.getCompanyCode());

            dayGoodVo1.setLastInflow(currentDay.getTotalMoney());
            dayGoodVo1.setLastPrice(currentDay.getEndPrice());
            dayGoodVo1.setLastRate(currentDay.getRate());

            dayGoodVo1.setDate(dayGood.getDate());
            if (nextDay != null && nextDay.getId() > 0) {
                dayGoodVo1.setTwoEndPrice(nextDay.getEndPrice());
                dayGoodVo1.setTwoStartPrice(nextDay.getStartPrice());
                dayGoodVo1.setTwoRate(nextDay.getRate());
                //如果跌幅超过-2必须卖掉
                if (nextDay.getRate() < sellMinPrice) {
                    if (dayGoodVo1.getPreRate() < 9.5) {
                        fztRateSum = fztRateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                        rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                    } else {
                        rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 2));
                    }
                } else {
                    rateSum = rateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    if (dayGoodVo1.getPreRate() < 9.5) {
                        fztRateSum = fztRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    }
                }
                if (dates.indexOf(dayGood.getDate()) > 1) {
                    threeDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dates.get(dates.indexOf(dayGood.getDate()) - 2));
                    if (threeDay != null && threeDay.getId() > 0) {
                        //如果第二天的涨幅大于三 留到第二天卖掉
                        if (nextDay.getRate() > 6) {
                            if (threeDay.getRate() < sellMinPrice) {
                                if (dayGoodVo1.getPreRate() < 9.5) {
                                    fztRateSum = fztRateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                                    rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 0.5));
                                } else {
                                    rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 2));
                                }
                            } else {
                                rateSum = rateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                                if (dayGoodVo1.getPreRate() < 9.5) {
                                    fztRateSum = fztRateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                                }
                            }
                        }
                        dayGoodVo1.setThreeRate(threeDay.getRate());
                    }
                }
                dayGoodVo1.setIncomeRate(BigDecimal.valueOf(nextDay.getRate()).add(BigDecimal.valueOf(currentDay.getEndPrice()).subtract(BigDecimal.valueOf(dayGood.getPrice()))).doubleValue());
                dayGoodVo1s.add(dayGoodVo1);
            }
        }
        FormatDate.getFormatDates(request);
        request.setAttribute("time", time);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("dayGoodVo1s", dayGoodVo1s);
        request.setAttribute("fztRateSum", fztRateSum);
        request.setAttribute("rateSum", rateSum);

        return "topTimeList";
    }

}
