package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.dtos.ProfessorDtoResponse;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.services.ProfessorService;

@RestController
@RequestMapping("/api/v1/university/professors")
@RequiredArgsConstructor
public class ProfessorController {

	private final ProfessorService professorService;

	@PostMapping("/")
	public ResponseEntity<Professor> addProfessor(@RequestBody ProfessorDtoRequest dto, HttpServletRequest request){
		Professor professor = professorService.add(dto);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(professor);
	}

	@GetMapping("/department/{departmentId}")
	public ResponseEntity<?> getProfessorsByDepartmentId(@PathVariable String departmentId) {
		List<ProfessorDtoResponse> responses = professorService.getAllByDepartmentId(departmentId);
		return  ResponseEntity.ok(responses);
	}


}
