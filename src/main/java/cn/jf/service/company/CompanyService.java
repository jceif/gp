package cn.jf.service.company;



import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.model.company.Company;


public interface CompanyService {

	 void insertCompany(Company company);

	 void deleteCompanyById(String companyId);

	 int deleteCompanyAll();

	 void updateCompany(Company company);

	 Company findCompanyById(String companyId);

	 List<Company> findCompanyList();

	 int deleteCompanyListByIds(String[] Ids);

	 int deleteCompanyList(List<Company> companys);

	 void insertCompanyList(List<Company> companys);

	 void updateCompanyList(List<Company> companys);

	 List<Company> findCompanyQuery(Map map);

	 PageUtil<Company> findCompanyQueryPage(Map map, String pageNo, String pageSize);

	 int findCountCompanyQuery(Map map);

}
