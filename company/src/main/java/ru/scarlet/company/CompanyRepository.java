package ru.scarlet.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
	Company findCompanyByCompanyName(String companyName);
}
