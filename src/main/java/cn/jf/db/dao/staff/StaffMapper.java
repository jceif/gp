package cn.jf.db.dao.staff;


import cn.jf.model.staff.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StaffMapper {

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
	public Staff findStaffByPhone(@Param("phone") String phone);

	public List<Staff> findStaffList();

	public int deleteStaffList(List<Staff> staffs);

	public int deleteStaffListByIds(String[] Ids);

	public int insertStaffList(List<Staff> staffs);

	public void updateStaffList(List<Staff> staffs);

	public List<Staff> findStaffQuery(Map map);

	public List<Staff> findStaffQueryPage(Map map);

	public int findCountStaffQuery(Map map);

}
