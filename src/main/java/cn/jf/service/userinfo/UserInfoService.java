package cn.jf.service.userinfo;


import cn.jf.common.PageUtil;
import cn.jf.model.staff.Staff;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

	public void insertUserInfo(Staff staff);

	public void deleteUserInfoById(String userInfoId);

	public int deleteUserInfoAll();

	public void updateUserInfo(Staff staff);

	public Staff findUserInfoById(String userInfoId);

	public List<Staff> findUserInfoList();

	public int deleteUserInfoListByIds(String[] Ids);

	public int deleteUserInfoList(List<Staff> staff);

	public void insertUserInfoList(List<Staff> staff);

	public void updateUserInfoList(List<Staff> staff);

	public List<Staff> findUserInfoQuery(Map map);

	public PageUtil<Staff> findUserInfoQueryPage(Map map, String pageNo, String pageSize);

	public int findCountUserInfoQuery(Map map);

}
