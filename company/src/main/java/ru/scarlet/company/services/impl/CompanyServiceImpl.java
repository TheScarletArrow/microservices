package ru.scarlet.company.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scarlet.company.Company;
import ru.scarlet.company.CompanyRepository;
import ru.scarlet.company.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyByName(String name) {
		return companyRepository.findCompanyByCompanyName(name);
	}
}
