package com.pack.companies.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pack.companies.Company;
import com.pack.companies.CompanyRepository;
import com.pack.companies.CompanyService;
import com.pack.companies.clients.ReviewClients;
import com.pack.companies.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService
{

	private CompanyRepository companyRepository;
	private ReviewClients reviewClients;
	
	public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClients reviewClients) {
		super();
		this.companyRepository = companyRepository;
		this.reviewClients = reviewClients;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public boolean updateCompany(Company company, Long id) {
	    return companyRepository.findById(id)
	        .map(companyToUpdate -> {
	            companyToUpdate.setDescription(company.getDescription());
	            companyToUpdate.setName(company.getName());
	            companyRepository.save(companyToUpdate);
	            return true;
	        })
	        .orElse(false);
	}

	@Override
	public void createCompany(Company company) {
		
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompany(Long id) {
		if(companyRepository.existsById(id)) {
		companyRepository.deleteById(id);
		return true;
		}else {
		return false;
		}
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		
		Company company = companyRepository.findById(reviewMessage.getCompanyId())
				.orElseThrow(() -> new NotFoundException("Company not found" + reviewMessage.getCompanyId()));
		Double averageRating = reviewClients.getAverageRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(averageRating);
		companyRepository.save(company);
	}
	


}
