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
    public String index(HttpServletRequest request, String date, String time, String price, String mainMoney,
        String rate,
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
    public String findTotalMoneyTopList(HttpServletRequest request, String dateStart, String dateEnd, String timeStart,
        String timeEnd) {
        List<Integer> dates = dayValueService.findDays();
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (StringUtils.isEmpty(timeStart)) {
            timeStart="1100";
        }
        if (StringUtils.isEmpty(timeEnd)) {
            timeEnd="1430";
        }
        if (StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }

        if (StringUtils.isEmpty(dateStart)) {
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
            dateStart = Calendar.getInstance().get(Calendar.YEAR) + "" + (month < 10 ? "0" + month : month) + "01";
        }
        if (StringUtils.isEmpty(dateEnd)) {
            dateEnd = simpleDateFormat.format(Calendar.getInstance().getTime());
        }
        Map<String, List<DayGood>> listMap = new LinkedHashMap<String, List<DayGood>>();
        BigDecimal daysRateSum = BigDecimal.valueOf(0);//总数据涨幅

        BigDecimal  fztDaysRateSum=BigDecimal.valueOf(0);//非涨停
        for (int i = 1; i < dates.size(); i++) {
            int date = dates.get(i);//小20180911
            int dateNext = dates.get(i - 1);//大20180912
            if (date >= Integer.parseInt(dateStart) && date <= Integer.parseInt(dateEnd)) {
                List<String> companyCodes = new ArrayList<String>();
                List<DayGood> dayGoodList = new ArrayList<DayGood>();
                BigDecimal v1030 []= tt(1030, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1100 []= tt(1100, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1130 []= tt(1130, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1300 []= tt(1300, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1330 []= tt(1330, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1400 []= tt(1400, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal v1430 []= tt(1430, timeStart, timeEnd, date, dateNext, companyCodes, dayGoodList);
                BigDecimal dayRateSum = v1030[0].add(v1100[0]).add(v1130[0]).add(v1300[0]).add(v1330[0]).add(v1400[0]).add(v1430[0]);//当天涨幅数据
                BigDecimal fztDayRateSum=v1030[1].add(v1100[1]).add(v1130[1]).add(v1300[1]).add(v1330[1]).add(v1400[1]).add(v1430[1]);//当天非涨停
                daysRateSum = daysRateSum.add(dayRateSum);
                fztDaysRateSum = fztDaysRateSum.add(fztDayRateSum);//非涨停 总值
                listMap.put(date + "_" + dayRateSum+"_"+fztDayRateSum, dayGoodList);
            }
        }
        FormatDate.getFormatDates(request);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("timeStart", timeStart);
        request.setAttribute("timeEnd", timeEnd);
        request.setAttribute("daysRateSum", daysRateSum);
        request.setAttribute("fztDaysRateSum", fztDaysRateSum);
        request.setAttribute("listMap", listMap);

        return "daygood/topGoodList";
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
        double sellMinPrice = -1.5;//最小卖出价格
        BigDecimal fztRateSum = BigDecimal.valueOf(0);//非涨停收益率
        BigDecimal rateSum = BigDecimal.valueOf(0);//所有收益率
        BigDecimal ztRateSum = BigDecimal.valueOf(0);//涨停收益率
        List<DayGoodVo1> dayGoodVo1s = new ArrayList<DayGoodVo1>();
        for (int i = 1; i < dayGoods.size(); i++) {
            DayGood  dayGood = dayGoods.get(i);
            //创业板
            if(dayGood.getCompanyCode().startsWith("300")) {
                continue;
            }
            if (dates.indexOf(dayGood.getDate()) == 0) {
                continue;
            }
            DayValue currentDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dayGood.getDate());
            DayValue nextDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dates.get(dates.indexOf(dayGood.getDate()) - 1));
            if (dayGood.getRate() < 9.5) {
                fztRateSum = fztRateSum.add(BigDecimal.valueOf(currentDay.getRate()).subtract(BigDecimal.valueOf(dayGood.getRate())));
            }
            rateSum = rateSum.add(BigDecimal.valueOf(currentDay.getRate()).subtract(BigDecimal.valueOf(dayGood.getRate())));
            DayGoodVo1 dayGoodVo1 = new DayGoodVo1();
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
              /*  if (nextDay.getRate() < sellMinPrice) {
                    if (dayGoodVo1.getPreRate() < 9.5) {
                        fztRateSum = fztRateSum.add(BigDecimal.valueOf(sellMinPrice - 1));
                        rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 1));
                    } else {
                        rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 3));
                    }
                } else {
                    rateSum = rateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    if (dayGoodVo1.getPreRate() < 9.5) {
                        fztRateSum = fztRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                    }
                }*/
                if (dayGoodVo1.getPreRate() < 9.5) {
                    fztRateSum = fztRateSum.add(BigDecimal.valueOf(nextDay.getRate()));
                }
                rateSum = rateSum.add(BigDecimal.valueOf(nextDay.getRate()));

                if (dates.indexOf(dayGood.getDate()) > 1) {
                    DayValue threeDay = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dates.get(dates.indexOf(dayGood.getDate()) - 2));
                    if (threeDay != null && threeDay.getId() > 0) {
                        //如果第二天的涨幅大于三 留到第二天卖掉
                        if (nextDay.getRate() > 6) {
                          /*  if (threeDay.getRate() < sellMinPrice) {
                                if (dayGoodVo1.getPreRate() < 9.5) {
                                    fztRateSum = fztRateSum.add(BigDecimal.valueOf(sellMinPrice - 1));
                                    rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 1));
                                } else {
                                    rateSum = rateSum.add(BigDecimal.valueOf(sellMinPrice - 2));
                                }
                            } else {
                                rateSum = rateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                                if (dayGoodVo1.getPreRate() < 9.5) {
                                    fztRateSum = fztRateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                                }
                            }*/
                            if (dayGoodVo1.getPreRate() < 9.5) {
                                fztRateSum = fztRateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                            }
                          rateSum = rateSum.add(BigDecimal.valueOf(threeDay.getRate()));
                        }
                        dayGoodVo1.setThreeRate(threeDay.getRate());
                    }
                }
                dayGoodVo1.setIncomeRate(BigDecimal.valueOf(nextDay.getRate()).add(BigDecimal.valueOf(currentDay.getRate()).subtract(BigDecimal.valueOf(dayGood.getRate()))).doubleValue());
                //统计涨停数据 赔付率
                if (dayGood.getRate() >= 9.5) {
                    ztRateSum = ztRateSum.add(BigDecimal.valueOf(dayGoodVo1.getIncomeRate()));
                }
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
        request.setAttribute("ztRateSum", ztRateSum);

        return "daygood/topTimeList";
    }


    /**
     *
     * @param timeValue 当前查询的时间
     * @param timeStart 开始时间
     * @param timeEnd 结束时间
     * @param date 当前查询的日期
     * @param dateNext 第二天的日期
     * @param companyCodes 公司代码
     * @param dayGoodList 公司
     * @return
     */
    private BigDecimal [] tt(int timeValue, String timeStart,String timeEnd,int date,int dateNext,List<String> companyCodes,List<DayGood> dayGoodList){
        BigDecimal value=BigDecimal.valueOf(0);
        BigDecimal fztDayRateSum=new BigDecimal(0);
        if (timeValue >= Integer.parseInt(timeStart) && timeValue <= Integer.parseInt(timeEnd)) {
            DayGood dayGood = dayGoodService.findByDateATime(date, timeValue);
            //非创业板
            if(!dayGood.getCompanyCode().startsWith("300")) {
                if (dayGood != null && !companyCodes.contains(dayGood.getCompanyCode())) {
                    DayValue dayValue = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), date);
                    if (dayValue != null) {
                        DayValue dayValueNext = dayValueService.findDayValueByIdAndDate(dayGood.getCompanyCode(), dateNext);
                        value = BigDecimal.valueOf(dayValue.getRate()).subtract(BigDecimal.valueOf(dayGood.getRate()));//当天赔付率
                        if (dayValueNext != null) {
                            value = value.add(BigDecimal.valueOf(dayValueNext.getRate()));//当天赔付率+第二天
                            dayGood.setNextRate(dayValueNext.getRate());
                        }
                        dayGood.setEndRate(dayValue.getRate());
                        dayGood.setEndMainMoney(dayValue.getTotalMoney());
                        companyCodes.add(dayGood.getCompanyCode());
                        dayGoodList.add(dayGood);
                        if (dayGood.getRate() < 9.5) {
                            fztDayRateSum = value;
                        }
                    }
                }
            }
        }
        return new BigDecimal[]{value,fztDayRateSum};
    }


}
