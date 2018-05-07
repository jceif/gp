package cn.jf.impl.dayvalue;

import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.DayValueMapper;
import cn.jf.model.dayvalue.DayValue;
import cn.jf.service.dayvalue.DayValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dayValueService")
public class DayValueServiceImpl implements DayValueService {

	@Autowired
	private DayValueMapper dayValueMapper;

	
	public void insertDayValue(DayValue dayValue)	{
		this.dayValueMapper.insertDayValue(dayValue);
	}


	public void deleteDayValueById(String dayValueId)	{
		this.dayValueMapper.deleteDayValueById(dayValueId);
	}


	public int deleteDayValueAll()	{
		return this.dayValueMapper.deleteDayValueAll();
	}

	
	public void updateDayValue(DayValue dayValue)	{
		this.dayValueMapper.updateDayValue(dayValue);
	}


	public DayValue findDayValueById(String dayValueId)	{
		return this.dayValueMapper.findDayValueById(dayValueId);
	}


	public List<DayValue> findDayValueList()	{
		return this.dayValueMapper.findDayValueList();
	}


	public int deleteDayValueListByIds(String[] Ids)	{
		return this.dayValueMapper.deleteDayValueListByIds(Ids);
	}

	
	public int deleteDayValueList(List<DayValue> dayValues)	{
		return this.dayValueMapper.deleteDayValueList(dayValues);
	}

	
	public void insertDayValueList(List<DayValue> dayValues)	{
		 this.dayValueMapper.insertDayValueList(dayValues);
	}

	
	public void updateDayValueList(List<DayValue> dayValues)	{
		 this.dayValueMapper.updateDayValueList(dayValues);
	}

	
	public List<DayValue> findDayValueQuery(Map map)	{
		return this.dayValueMapper.findDayValueQuery(map);
	}

	
	public PageUtil<DayValue> findDayValueQueryPage(Map map,String pageNo,String pageSize)	{
		int totalCount = dayValueMapper.findCountDayValueQuery(map);
		PageUtil pageUtil = new PageUtil(pageNo, totalCount, pageSize);
		if (totalCount != 0) {
				map.put("startIndex",pageUtil.getStartRecord());
				map.put("limit",pageUtil.getPageSize());
				List<DayValue>dayValueList = this.dayValueMapper.findDayValueQueryPage(map);
				pageUtil.setRecords(dayValueList);
		}
		return pageUtil;
	}

	
	public int findCountDayValueQuery(Map map)	{
		return this.dayValueMapper.findCountDayValueQuery(map);
	}

}
