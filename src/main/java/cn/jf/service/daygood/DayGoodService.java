package cn.jf.service.daygood;



import java.util.List;
import java.util.Map;

import cn.jf.common.PageUtil;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;


public interface DayGoodService {

 void insertDayGood(DayGood dayGood);

 void deleteDayGoodById(String dayGoodId);

 int deleteDayGoodAll();

 void updateDayGood(DayGood dayGood);

 DayGood findDayGoodById(String dayGoodId);

 List<DayGood> findDayGoodList();

 int deleteDayGoodListByIds(String[] Ids);

 int deleteDayGoodList(List<DayGood> dayGoods);

 void insertDayGoodList(List<DayGood> dayGoods);

 void updateDayGoodList(List<DayGood> dayGoods);

 List<DayGood> findDayGoodQuery(Map map);

 PageUtil<DayGood> findDayGoodQueryPage(Map map, String pageNo, String pageSize);

 int findCountDayGoodQuery(Map map);

 List<DayGoodVo> findDayGoodNowQuery(Map map);


}
