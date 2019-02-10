package cn.jf.impl.dayvalue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.dayvalue.DayValueMapper;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.model.dayvalue.DayValueVo1;
import cn.jf.service.dayvalue.DayValueService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
  public List<DayValue> dayValueUpList(String date1,String date2,String date3,String date4,String date5,String inDate,int limit) {
    List<DayValue> dayValueList = new ArrayList<>();
    List<DayValue> dayValues = dayValueMapper.dayValueUpList(date1, date2, date3, date4, date5, inDate);
    if (dayValues != null) {
      for (DayValue dayValue : dayValues) {
        if (dayValue.getCompanyCode().startsWith("300")) {
          continue;
        }
        //计算前三天的跌幅之和
        Double preSumRate = dayValueMapper.dayValuePreSumRate(dayValue.getCompanyCode(), Integer.parseInt(date5));
        //如果跌幅达不到4.5，不算收益
        if (preSumRate < -4.49) {
          //inDate 不统计收益
          if (inDate != null && !StringUtils.isEmpty(inDate)) {
            List<DayValue> rateList = dayValueMapper.dayValueSumRate(dayValue.getCompanyCode(), Integer.parseInt(inDate), limit);
            BigDecimal sumRate = BigDecimal.valueOf(0);
            if (rateList != null) {
              for (DayValue value : rateList) {
                sumRate = sumRate.add(BigDecimal.valueOf(value.getRate()));
              }
              dayValue.setSumRate(sumRate.doubleValue());
            }
          }
          dayValueList.add(dayValue);
        }
      }
    }
    return dayValueList;
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
