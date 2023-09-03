package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;
import ru.scarlet.company.services.ExpertiseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/university/expertise")
public class ExpertiseController {

	private final ExpertiseService expertiseService;

	@PostMapping("/")
	public ResponseEntity<Expertise> addExpertise(@RequestBody ExpertiseDto expertiseDto, HttpServletRequest request){
		Expertise expertise = expertiseService.create(expertiseDto);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(expertise);
	}

}
