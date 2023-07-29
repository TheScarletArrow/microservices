package ru.scarlet.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.salary.services.SalaryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/salary")
public class SalaryController {

	private final SalaryService salaryService;

	@GetMapping
	public ResponseEntity<?> getSalary(){
		return new ResponseEntity<>(salaryService.getSalary(), HttpStatus.OK);
	}
}
