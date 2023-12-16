package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.services.DeanService;

import java.net.URI;

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

//	@GetMapping("/")
//	public ResponseEntity<List<DeanGetResponse>> getAll(){
//		return ResponseEntity.ok(deanService.getAll());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<DeanGetResponse> getDeanById(@PathVariable Integer id){
		return ResponseEntity.ok(deanService.getDeanDtoById(id));
	}
}
