package cn.jf.db.dao;
import java.util.List;
import java.util.Map;
import cn.jf.model.company.Company;

public interface CompanyMapper {

 void insertCompany(Company company);

 void deleteCompanyById(String companyId);

 int deleteCompanyAll();

 void updateCompany(Company company);

 Company findCompanyById(String companyId);

 List<Company> findCompanyList();

 int deleteCompanyList(List<Company> companys);

 int deleteCompanyListByIds(String[] Ids);

 int insertCompanyList(List<Company> companys);

 void updateCompanyList(List<Company> companys);

 List<Company> findCompanyQuery(Map map);

 List<Company> findCompanyQueryPage(Map map);

 int findCountCompanyQuery(Map map);

}
