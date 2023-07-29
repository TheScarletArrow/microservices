package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.Company;
import ru.scarlet.company.CompanyRepository;
import ru.scarlet.company.services.CompanyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	@Override
	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyByName(String name) {
		return companyRepository.findCompanyByCompanyName(name);
	}
}
