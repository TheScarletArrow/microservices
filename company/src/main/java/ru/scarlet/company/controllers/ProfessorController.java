package ru.scarlet.company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.services.ProfessorService;

@RestController
@RequestMapping("/api/v1/university/professors")
@RequiredArgsConstructor
public class ProfessorController {

	private final ProfessorService professorService;

	@PostMapping("/")
	public ResponseEntity<Professor> addProfessor(@RequestBody ProfessorDtoRequest dto){
		Professor professor = professorService.add(dto);
		return ResponseEntity.ok(professor);
	}
}
