package cn.jf.service.dayvalue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.model.dayvalue.DayValue;
import org.springframework.web.bind.annotation.RequestParam;


public interface DayValueService {

  void insertDayValue(DayValue dayValue);

  void deleteDayValueById(String dayValueId);

  int deleteDayValueAll();

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

  List<DayValue> dayValueTop5(String companyCode);

  Double dayValueAverage(String companyCode);

  List<DayValue> dayValueUpList();


}
