package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.services.FacultyService;

@RestController
@RequestMapping("/api/v1/university/faculties")
@RequiredArgsConstructor
public class FacultyController {

	private final FacultyService facultyService;

	@PostMapping("/")
	public ResponseEntity<FacultyResponse> createFaculty(@RequestBody FacultyRequest facultyRequest, HttpServletRequest request){
		FacultyResponse faculty = facultyService.createFaculty(facultyRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(faculty);
	}
}
