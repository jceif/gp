package cn.jf.impl.dayvalue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.dayvalue.DayValueMapper;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVo1;
import cn.jf.service.dayvalue.DayValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dayValueService")
public class DayValueServiceImpl implements DayValueService {

  @Autowired
  private DayValueMapper dayValueMapper;


  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

  public void insertDayValue(DayValue dayValue) {
    this.dayValueMapper.insertDayValue(dayValue);
  }


  public void deleteDayValueById(String dayValueId) {
    this.dayValueMapper.deleteDayValueById(dayValueId);
  }




  public void updateDayValue(DayValue dayValue) {
    this.dayValueMapper.updateDayValue(dayValue);
  }


  public DayValue findDayValueById(String dayValueId) {
    return this.dayValueMapper.findDayValueById(dayValueId);
  }


  public List<DayValue> findDayValueList() {
    return this.dayValueMapper.findDayValueList();
  }


  public int deleteDayValueListByIds(String[] Ids) {
    return this.dayValueMapper.deleteDayValueListByIds(Ids);
  }


  public int deleteDayValueList(List<DayValue> dayValues) {
    return this.dayValueMapper.deleteDayValueList(dayValues);
  }


  public void insertDayValueList(List<DayValue> dayValues) {
    this.dayValueMapper.insertDayValueList(dayValues);
  }


  public void updateDayValueList(List<DayValue> dayValues) {
    this.dayValueMapper.updateDayValueList(dayValues);
  }


  public List<DayValue> findDayValueQuery(Map map) {
    return this.dayValueMapper.findDayValueQuery(map);
  }


  public PageUtil<DayValue> findDayValueQueryPage(Map map, String pageNo, String pageSize) {
    int totalCount = dayValueMapper.findCountDayValueQuery(map);
    PageUtil pageUtil = new PageUtil(pageNo, totalCount, pageSize);
    if (totalCount != 0) {
      map.put("startIndex", pageUtil.getStartRecord());
      map.put("limit", pageUtil.getPageSize());
      List<DayValue> dayValueList = this.dayValueMapper.findDayValueQueryPage(map);
      pageUtil.setRecords(dayValueList);
    }
    return pageUtil;
  }


  public int findCountDayValueQuery(Map map) {
    return this.dayValueMapper.findCountDayValueQuery(map);
  }





  @Override
  public List<DayValue> dayValueUpList() {
    return dayValueMapper.dayValueUpList();
  }



  @Override
  public DayValue findDayValueByIdAndDate(String companyCode, int date) {
    return dayValueMapper.findDayValueByIdAndDate(companyCode,date);
  }

  @Override
  public Integer findCountByCompanyCode(String companyCode,Integer date) {
    return dayValueMapper.findCountByCompanyCode(companyCode,date);
  }

    @Override
    public List<Integer> findDays() {
        return dayValueMapper.findDays();
    }

  @Override
  public DayValue findByCodeADate(String companyCode,int date) {
    return dayValueMapper.findByCodeADate(companyCode,date);
  }

  @Override
  public List<DayValue> findDayValueZt(float rate, float totalMoney1,float totalMoney2, int startDate, int endDate) {
    return dayValueMapper.findDayValueZt(rate,totalMoney1,totalMoney2,startDate,endDate);
  }

  @Override
  public List<DayValueVo1> findByInflowDays(int startDate, int endDate, int currentDate) {
    return dayValueMapper.findByInflowDays(startDate,endDate,currentDate);
  }


}
