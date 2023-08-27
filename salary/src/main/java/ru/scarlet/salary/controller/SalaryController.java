package ru.scarlet.salary.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.salary.client.AuthClient;
import ru.scarlet.salary.dto.MonthyAmount;
import ru.scarlet.salary.dto.SalaryIn;
import ru.scarlet.salary.dto.SalaryOut;
import ru.scarlet.salary.services.SalaryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/salary")
public class SalaryController {

	private final SalaryService salaryService;

	private final AuthClient authClient;

	@PostMapping("/")
	public ResponseEntity<SalaryOut> createSalary(HttpServletRequest request, @RequestBody SalaryIn salary) {
		return new ResponseEntity<>(salaryService.createSalary(salary, request), HttpStatus.CREATED);
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
	public ResponseEntity<List<SalaryOut>> getAllByUsername(HttpServletRequest httpServletRequest) {
		String username = authClient.getUsername(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
		return ResponseEntity.ok(salaryService.getAllByUsername(username));
	}

	@GetMapping("/aggregate")
	public ResponseEntity<?> getAllGrouped(HttpServletRequest httpServletRequest) {
		String username = authClient.getUsername(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));

		return ResponseEntity.ok(salaryService.getGrouped(username));
	}

	@GetMapping("/sum")
	public ResponseEntity<List<MonthyAmount>> sum(HttpServletRequest httpServletRequest) {
		String username = authClient.getUsername(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));

		return ResponseEntity.ok(salaryService.findSum(username));
	}
}
