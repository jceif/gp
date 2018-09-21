package cn.jf.db.dao;

import java.util.List;
import java.util.Map;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.daygood.DayGoodVo1;
import org.apache.ibatis.annotations.Param;


public interface DayGoodMapper {

  void insertDayGood(DayGood dayGood);

  void deleteDayGoodById(String dayGoodId);

  int deleteDayGoodAll();

  void updateDayGood(DayGood dayGood);

  DayGood findDayGoodById(String dayGoodId);

  List<DayGood> findDayGoodList();



  int deleteDayGoodListByIds(String[] Ids);



  List<DayGoodVo> findDayGoodNowQuery(Map map);

  List<DayGood> findDayGoodQuery(Map map);

  List<DayGood> findDayGoodQueryPage(Map map);

  int findCountDayGoodQuery(Map map);


  List<DayGoodVo1> findDGLastRateByTimeAndInflow(@Param("time") String time, @Param("rate") float rate,@Param("inflow") float inflow);




}
