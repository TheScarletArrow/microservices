package ru.scarlet.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.services.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

	private final CompanyService companyService;

	@GetMapping("/")
	public ResponseEntity<?> getCompanies(){
		return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Company> getCompanyByName(@PathVariable String name){
		return new ResponseEntity<>(companyService.getCompanyByName(name), HttpStatus.OK);
	}
}
