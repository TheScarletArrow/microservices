package ru.scarlet.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.services.CompanyService;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

	private final CompanyService companyService;

	@Autowired // Add this annotation
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/")
	public ResponseEntity<?> getCompanies(){
		return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Company> getCompanyByName(@PathVariable String name){
		return new ResponseEntity<>(companyService.getCompanyByName(name), HttpStatus.OK);
	}
}
