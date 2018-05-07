package cn.jf.db.dao;



import java.util.List;
import java.util.Map;
import cn.jf.model.dayvalue.DayValue;


public interface DayValueMapper {

	 void insertDayValue(DayValue dayValue);

	 void deleteDayValueById(String dayValueId);

	 int deleteDayValueAll();

	 void updateDayValue(DayValue dayValue);

	 DayValue findDayValueById(String dayValueId);

	 List<DayValue> findDayValueList();

	 int deleteDayValueList(List<DayValue> dayValues);

	 int deleteDayValueListByIds(String[] Ids);

	 int insertDayValueList(List<DayValue> dayValues);

	 void updateDayValueList(List<DayValue> dayValues);

	 List<DayValue> findDayValueQuery(Map map);

	 List<DayValue> findDayValueQueryPage(Map map);

	 int findCountDayValueQuery(Map map);

}
