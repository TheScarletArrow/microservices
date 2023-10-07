package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.services.DeanService;
import ru.scarlet.company.services.FacultyService;

@RestController
@RequestMapping("/api/v1/university/faculties")
@RequiredArgsConstructor
public class FacultyController {

	private final FacultyService facultyService;

	private final DeanService deanService;

	@PostMapping("/")
	public ResponseEntity<FacultyResponse> createFaculty(@RequestBody FacultyRequest facultyRequest, HttpServletRequest request){
		FacultyResponse faculty = facultyService.createFaculty(facultyRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(faculty);
	}

	@PostMapping("{facId}/dean/{id}")
	public ResponseEntity<?> addDeanToFaculty(@PathVariable Integer id, @PathVariable Integer facId){
		facultyService.setDeanAndFac(facId,id);
		return ResponseEntity.noContent().build();
	}
}
