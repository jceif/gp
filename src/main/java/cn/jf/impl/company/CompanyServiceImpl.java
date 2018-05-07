package cn.jf.impl.company;


import java.util.List;
import java.util.Map;
import cn.jf.common.PageUtil;
import cn.jf.db.dao.CompanyMapper;
import cn.jf.model.company.Company;
import cn.jf.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	
	public void insertCompany(Company company)	{
		this.companyMapper.insertCompany(company);
	}

	
	public void deleteCompanyById(String companyId)	{
		this.companyMapper.deleteCompanyById(companyId);
	}

	
	public int deleteCompanyAll()	{
		return this.companyMapper.deleteCompanyAll();
	}

	
	public void updateCompany(Company company)	{
		this.companyMapper.updateCompany(company);
	}

	
	public Company findCompanyById(String companyId)	{
		return this.companyMapper.findCompanyById(companyId);
	}

	
	public List<Company> findCompanyList()	{
		return this.companyMapper.findCompanyList();
	}

	
	public int deleteCompanyListByIds(String[] Ids)	{
		return this.companyMapper.deleteCompanyListByIds(Ids);
	}

	
	public int deleteCompanyList(List<Company> companys)	{
		return this.companyMapper.deleteCompanyList(companys);
	}

	
	public void insertCompanyList(List<Company> companys)	{
		 this.companyMapper.insertCompanyList(companys);
	}

	
	public void updateCompanyList(List<Company> companys)	{
		 this.companyMapper.updateCompanyList(companys);
	}

	
	public List<Company> findCompanyQuery(Map map)	{
		return this.companyMapper.findCompanyQuery(map);
	}

	
	public PageUtil<Company> findCompanyQueryPage(Map map,String pageNo,String pageSize)	{
		int totalCount = companyMapper.findCountCompanyQuery(map);
		PageUtil pageUtil = new PageUtil(pageNo, totalCount, pageSize);
		if (totalCount != 0) {
				map.put("startIndex",pageUtil.getStartRecord());
				map.put("limit",pageUtil.getPageSize());
				List<Company>companyList = this.companyMapper.findCompanyQueryPage(map);
				pageUtil.setRecords(companyList);
		}
		return pageUtil;
	}

	
	public int findCountCompanyQuery(Map map)	{
		return this.companyMapper.findCountCompanyQuery(map);
	}

}
