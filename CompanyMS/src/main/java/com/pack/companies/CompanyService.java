package com.pack.companies;

import java.util.List;

import com.pack.companies.dto.ReviewMessage;


public interface CompanyService {
	List<Company> getAllCompanies();
	
	boolean updateCompany(Company company, Long id);
	
	void createCompany(Company company);
	
	boolean deleteCompany(Long id);
	
	Company getCompanyById(Long id);

	void updateCompanyRating(ReviewMessage reviewMessage);
}
