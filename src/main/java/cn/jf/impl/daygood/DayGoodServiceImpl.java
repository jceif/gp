package cn.jf.impl.daygood;


import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.DayGoodMapper;
import cn.jf.model.daygood.DayGood;
import cn.jf.model.daygood.DayGoodVo;
import cn.jf.service.daygood.DayGoodService;
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

	
	public int deleteDayGoodList(List<DayGood> dayGoods)	{
		return this.dayGoodMapper.deleteDayGoodList(dayGoods);
	}

	
	public void insertDayGoodList(List<DayGood> dayGoods)	{
		 this.dayGoodMapper.insertDayGoodList(dayGoods);
	}

	
	public void updateDayGoodList(List<DayGood> dayGoods)	{
		 this.dayGoodMapper.updateDayGoodList(dayGoods);
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

}
