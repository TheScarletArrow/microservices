package ru.scarlet.company.services;

import ru.scarlet.company.Company;

import java.util.List;

public interface CompanyService {
	List<Company> getCompanies();

	Company getCompanyByName(String name);
}
