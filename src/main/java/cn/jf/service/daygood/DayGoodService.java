package cn.jf.service.daygood;



import java.util.List;
import java.util.Map;

import cn.jf.common.PageUtil;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.daygood.DayGoodVo1;
import cn.jf.model.dayvalue.DayValue;


public interface DayGoodService {

 void insertDayGood(DayGood dayGood);

 void deleteDayGoodById(String dayGoodId);

 int deleteDayGoodAll();

 void updateDayGood(DayGood dayGood);

 DayGood findDayGoodById(String dayGoodId);

 List<DayGood> findDayGoodList();

 int deleteDayGoodListByIds(String[] Ids);

 List<DayGood> findDayGoodQuery(Map map);

 PageUtil<DayGood> findDayGoodQueryPage(Map map, String pageNo, String pageSize);

 int findCountDayGoodQuery(Map map);

 List<DayGoodVo> findDayGoodNowQuery(Map map);

 List<DayGoodVo1> findDGLastRateByTimeAndInflow(String time, float rate,float inflow,int dateStart, int dateEnd);

 List<DayGood> findTopOneByTime(String time, int dateStart, int dateEnd);



 DayGood findByDateATime(int date,int time);



}
