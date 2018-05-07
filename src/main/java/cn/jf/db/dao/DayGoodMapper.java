package cn.jf.db.dao;

import java.util.List;
import java.util.Map;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;


public interface DayGoodMapper {

  void insertDayGood(DayGood dayGood);

  void deleteDayGoodById(String dayGoodId);

  int deleteDayGoodAll();

  void updateDayGood(DayGood dayGood);

  DayGood findDayGoodById(String dayGoodId);

  List<DayGood> findDayGoodList();

  int deleteDayGoodList(List<DayGood> dayGoods);

  int deleteDayGoodListByIds(String[] Ids);

  int insertDayGoodList(List<DayGood> dayGoods);

  void updateDayGoodList(List<DayGood> dayGoods);

  List<DayGoodVo> findDayGoodNowQuery(Map map);

  List<DayGood> findDayGoodQuery(Map map);

  List<DayGood> findDayGoodQueryPage(Map map);

  int findCountDayGoodQuery(Map map);




}
