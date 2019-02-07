package cn.jf.db.dao.dayvalue;



import java.util.List;
import java.util.Map;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVo1;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


public interface DayValueMapper {

	 void insertDayValue(DayValue dayValue);

	 void deleteDayValueById(String dayValueId);



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




	List<DayValue> dayValueUpList(@Param("date1") String date1,@Param("date2") String date2,@Param("date3") String date3);



	DayValue findDayValueByIdAndDate(@Param("companyCode") String companyCode,@Param("date") int date);


	Integer findCountByCompanyCode(@Param("companyCode") String companyCode,@Param("date") Integer date);


	List<Integer> findDays();
	DayValue findByCodeADate(@Param("companyCode") String companyCode,@Param("date") int date);


	List<DayValue> findDayValueZt(@Param("rate") float rate, @Param("totalMoney1") float totalMoney1, @Param("totalMoney2") float totalMoney2,@Param("startDate") int startDate,@Param("endDate") int endDate);

	List<DayValueVo1> findByInflowDays(@Param("startDate") int startDate, @Param("endDate") int endDate, @Param("currentDate") int currentDate);

	Double dayValueSumRate(@Param("code") String code,@Param("date") int date,@Param("limit") int limit);


}
