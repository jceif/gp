package cn.jf.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class FormatDate {

  static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

  /*日期函数*/
  public static void getFormatDates_month(HttpServletRequest request) {
    List<String> dateStarts = new ArrayList<String>();
    List<String> dateEnds = new ArrayList<String>();
    Calendar calendar = Calendar.getInstance();
    for (int i = 1; i < 13; i++) {
      int year = calendar.get(Calendar.YEAR);    //获取年
      int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
      int last = calendar.getActualMaximum(calendar.DAY_OF_MONTH);    //获取本月最大天数
      dateStarts.add(year+""+(month<10?"0"+month:month)+"01");
      dateEnds.add(year+""+(month<10?"0"+month:month)+""+last);
      calendar.add(Calendar.MONTH, -1);
    }
    request.setAttribute("dateStarts", dateStarts);
    request.setAttribute("dateEnds", dateEnds);
  }


  /*日期函数*/
  public static void getFormatDates_day(HttpServletRequest request) {
    List<String> preDates = new ArrayList<String>();
    List<String> pre1Dates = new ArrayList<String>();
    List<String> pre2Dates = new ArrayList<String>();
    Calendar calendar = Calendar.getInstance();
    for (int i = 0; i < 90; i++) {
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
