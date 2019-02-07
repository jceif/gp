package cn.jf.service.dayvalue;

import cn.jf.common.PageUtil;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVo1;

import java.util.List;
import java.util.Map;


public interface DayValueService {

  void insertDayValue(DayValue dayValue);

  void deleteDayValueById(String dayValueId);


  void updateDayValue(DayValue dayValue);

  DayValue findDayValueById(String dayValueId);

  List<DayValue> findDayValueList();

  int deleteDayValueListByIds(String[] Ids);

  int deleteDayValueList(List<DayValue> dayValues);

  void insertDayValueList(List<DayValue> dayValues);

  void updateDayValueList(List<DayValue> dayValues);

  List<DayValue> findDayValueQuery(Map map);

  PageUtil<DayValue> findDayValueQueryPage(Map map, String pageNo, String pageSize);

  int findCountDayValueQuery(Map map);


  List<DayValue> dayValueUpList(String date1,String date2, String date3);


  DayValue findDayValueByIdAndDate( String companyCode,int date);

  Integer findCountByCompanyCode(String companyCode,Integer date);

  List<Integer> findDays();

  DayValue findByCodeADate(String companyCode, int date);

  List<DayValue> findDayValueZt(float rate, float totalMoney1,float totalMoney2,int startDate,int endDate);

  List<DayValueVo1> findByInflowDays( int startDate, int endDate, int currentDate);

}
