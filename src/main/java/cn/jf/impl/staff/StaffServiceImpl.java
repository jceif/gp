package cn.jf.impl.staff;


import cn.jf.common.PageUtil;
import cn.jf.db.dao.staff.StaffMapper;
import cn.jf.model.staff.Staff;
import cn.jf.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;

	@Override
	public void insertStaff(Staff staff)	{
		this.staffMapper.insertStaff(staff);
	}

	@Override
	public void deleteStaffById(String staffId)	{
		this.staffMapper.deleteStaffById(staffId);
	}

	@Override
	public int deleteStaffAll()	{
		return this.staffMapper.deleteStaffAll();
	}

	@Override
	public void updateStaff(Staff staff)	{
		this.staffMapper.updateStaff(staff);
	}

	@Override
	public Staff findStaffById(String staffId)	{
		return this.staffMapper.findStaffById(staffId);
	}

	@Override
	public Staff findStaffByPhone(String phone) {
		return this.staffMapper.findStaffByPhone(phone);
	}

	@Override
	public List<Staff> findStaffList()	{
		return this.staffMapper.findStaffList();
	}

	@Override
	public int deleteStaffListByIds(String[] Ids)	{
		return this.staffMapper.deleteStaffListByIds(Ids);
	}

	@Override
	public int deleteStaffList(List<Staff> staffs)	{
		return this.staffMapper.deleteStaffList(staffs);
	}

	@Override
	public void insertStaffList(List<Staff> staffs)	{
		 this.staffMapper.insertStaffList(staffs);
	}

	@Override
	public void updateStaffList(List<Staff> staffs)	{
		 this.staffMapper.updateStaffList(staffs);
	}

	@Override
	public List<Staff> findStaffQuery(Map map)	{
		return this.staffMapper.findStaffQuery(map);
	}

	@Override
	public PageUtil<Staff> findStaffQueryPage(Map map, String pageNo, String pageSize)	{
		int totalCount = staffMapper.findCountStaffQuery(map);
		PageUtil pageUtil = new PageUtil(pageNo, totalCount, pageSize);
		if (totalCount != 0) {
				map.put("startIndex",pageUtil.getStartRecord());
				map.put("limit",pageUtil.getPageSize());
				List<Staff>staffList = this.staffMapper.findStaffQueryPage(map);
				pageUtil.setRecords(staffList);
		}
		return pageUtil;
	}

	@Override
	public int findCountStaffQuery(Map map)	{
		return this.staffMapper.findCountStaffQuery(map);
	}

}
