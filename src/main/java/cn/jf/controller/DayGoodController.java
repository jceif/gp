package cn.jf.controller;

import cn.jf.model.daygood.DayGoodVo;
import cn.jf.service.dayvalue.DayValueService;
import com.alibaba.druid.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;

import cn.jf.model.daygood.DayGood;
import cn.jf.service.daygood.DayGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dg")
public class DayGoodController {
    @Autowired
    private DayGoodService dayGoodService;
    @Autowired
    private DayValueService dayValueService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping("/index")
    public String index(HttpServletRequest request, String date, String time, String price, String mainMoney, String rate, String preDate1, String preDate2, String type, String marketWorth1, String marketWorth2) {
        Calendar calendar = Calendar.getInstance();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(date)) {
            date = simpleDateFormat.format(calendar.getTime());
        }
        map.put("date", date);
        if (StringUtils.isEmpty(time)) {
            time = "1000";
        }
        map.put("time", time);
        if (StringUtils.isEmpty(time)) {
            map.put("preTime1", "930");
        } else {
            if (time.contains("30")) {
                map.put("preTime1", Integer.parseInt(time) - 30);
            } else {
                map.put("preTime1", Integer.parseInt(time) - 70);
            }
        }
        if (!StringUtils.isEmpty(time) && Integer.parseInt(time) >= 1030) {
            map.put("preTime2", Integer.parseInt(time) - 100);
        } else {
            map.put("preTime2", map.get("preTime1"));
        }
        if (StringUtils.isEmpty(price)) {
            price = "21.24";
        }
        map.put("price", price);
        if (StringUtils.isEmpty(mainMoney)) {
            mainMoney = "500";
        }
        map.put("mainMoney", mainMoney);    //主要资金
        if (StringUtils.isEmpty(rate)) {
            rate = "1";
        }
        map.put("rate", rate);
        if (StringUtils.isEmpty(preDate1)) {
            preDate1 = (Integer.parseInt(rate) - 1) + "";
        }
        map.put("preDayDate1", preDate1);
        map.put("preDayDate2", preDate2);
        map.put("type", type);
        map.put("marketWorth1", marketWorth1);
        map.put("marketWorth2", marketWorth2);

        List<DayGoodVo> dayGoods = dayGoodService.findDayGoodNowQuery(map);
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
        getFormatDates(request);
        return "index";
    }

    private void getFormatDates(HttpServletRequest request) {
        List<String> preDates = new ArrayList<String>();
        List<String> pre1Dates = new ArrayList<String>();
        List<String> pre2Dates = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            preDates.add(simpleDateFormat.format(calendar.getTime()));
            if (i > 0) {
                pre1Dates.add(simpleDateFormat.format(calendar.getTime()));
            }
            if (i > 1) {
                pre2Dates.add(simpleDateFormat.format(calendar.getTime()));
            }
            calendar.add(Calendar.DATE, -1);
        }
        request.setAttribute("formatDates", preDates);
        request.setAttribute("formatDates1", pre1Dates);
        request.setAttribute("formatDates2", pre2Dates);
    }

}
