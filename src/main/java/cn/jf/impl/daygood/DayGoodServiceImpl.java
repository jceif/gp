package cn.jf.impl.daygood;


import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.DayGoodMapper;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.model.daygood.DayGoodVo1;
import cn.jf.service.daygood.DayGoodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dayGoodService")
public class DayGoodServiceImpl implements DayGoodService {

	@Autowired
	private DayGoodMapper dayGoodMapper;

	
	public void insertDayGood(DayGood dayGood)	{
		this.dayGoodMapper.insertDayGood(dayGood);
	}

	
	public void deleteDayGoodById(String dayGoodId)	{
		this.dayGoodMapper.deleteDayGoodById(dayGoodId);
	}

	
	public int deleteDayGoodAll()	{
		return this.dayGoodMapper.deleteDayGoodAll();
	}

	
	public void updateDayGood(DayGood dayGood)	{
		this.dayGoodMapper.updateDayGood(dayGood);
	}

	
	public DayGood findDayGoodById(String dayGoodId)	{
		return this.dayGoodMapper.findDayGoodById(dayGoodId);
	}

	
	public List<DayGood> findDayGoodList()	{
		return this.dayGoodMapper.findDayGoodList();
	}

	
	public int deleteDayGoodListByIds(String[] Ids)	{
		return this.dayGoodMapper.deleteDayGoodListByIds(Ids);
	}

	


	
	public List<DayGood> findDayGoodQuery(Map map)	{
		return this.dayGoodMapper.findDayGoodQuery(map);
	}

	
	public PageUtil<DayGood> findDayGoodQueryPage(Map map,String pageNo,String pageSize)	{
		int totalCount = dayGoodMapper.findCountDayGoodQuery(map);
		PageUtil pageUtil = new PageUtil(pageNo, totalCount, pageSize);
		if (totalCount != 0) {
				map.put("startIndex",pageUtil.getStartRecord());
				map.put("limit",pageUtil.getPageSize());
				List<DayGood>dayGoodList = this.dayGoodMapper.findDayGoodQueryPage(map);
				pageUtil.setRecords(dayGoodList);
		}
		return pageUtil;
	}

	
	public int findCountDayGoodQuery(Map map)	{
		return this.dayGoodMapper.findCountDayGoodQuery(map);
	}

	@Override
	public List<DayGoodVo> findDayGoodNowQuery(Map map) {
		return this.dayGoodMapper.findDayGoodNowQuery(map);
	}

	@Override
	public List<DayGoodVo1> findDGLastRateByTimeAndInflow(String time, float rate, float inflow,int dateStart,int dateEnd) {
		return dayGoodMapper.findDGLastRateByTimeAndInflow(time,rate,inflow,dateStart,dateEnd);
	}

	@Override
	public List<DayGood> findTopOneByTime(String time, int dateStart, int dateEnd) {
		return dayGoodMapper.findTopOneByTime(time,dateStart,dateEnd);
	}

	@Override
	public DayGood findByDateATime(int date, int time) {
		return  dayGoodMapper.findByDateATime(date,time);
	}


}
