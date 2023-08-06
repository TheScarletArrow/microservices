package ru.scarlet.salary.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.salary.dto.SalaryIn;
import ru.scarlet.salary.dto.SalaryOut;
import ru.scarlet.salary.services.SalaryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/salary")
public class SalaryController {

	private final SalaryService salaryService;



	@PostMapping("/")
	public ResponseEntity<SalaryOut> createSalary(HttpServletRequest request, @RequestBody SalaryIn salary){
		return new ResponseEntity<> (salaryService.createSalary(salary, request), HttpStatus.CREATED);
	}

	@PostMapping("/report")
	public ResponseEntity<byte[]> toExcel(HttpServletRequest request, @RequestParam String fileName) {
		// в новый сервис
		byte[] report = salaryService.writeToExcel(request, fileName);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
				.body(report);
	}



	@GetMapping("/")
	public ResponseEntity<List<SalaryOut>> getAll(HttpServletRequest httpServletRequest){
		return ResponseEntity.ok(salaryService.getAll(httpServletRequest));
	}
}
