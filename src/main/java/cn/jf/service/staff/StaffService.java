package cn.jf.service.staff;


import cn.jf.common.PageUtil;
import cn.jf.model.staff.Staff;

import java.util.List;
import java.util.Map;

public interface StaffService {

	public void insertStaff(Staff staff);

	public void deleteStaffById(String staffId);

	public int deleteStaffAll();

	public void updateStaff(Staff staff);

	public Staff findStaffById(String staffId);

	/**
	 * 根据 电话号码查询信息
	 * @param phone
	 * @return
	 */
	public Staff findStaffByPhone(String phone);


	public List<Staff> findStaffList();

	public int deleteStaffListByIds(String[] Ids);

	public int deleteStaffList(List<Staff> staffs);

	public void insertStaffList(List<Staff> staffs);

	public void updateStaffList(List<Staff> staffs);

	public List<Staff> findStaffQuery(Map map);

	public PageUtil<Staff> findStaffQueryPage(Map map, String pageNo, String pageSize);

	public int findCountStaffQuery(Map map);

}
