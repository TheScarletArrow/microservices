package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.services.DeanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/university/deans")
public class DeanController {

	private final DeanService deanService;

	@PostMapping("/")
	public ResponseEntity<Dean> createDean(@RequestBody DeanRequest deanRequest, HttpServletRequest request){
		Dean dean = deanService.createDean(deanRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(dean);
	}
}
