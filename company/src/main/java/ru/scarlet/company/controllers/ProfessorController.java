package ru.scarlet.company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.services.ProfessorService;

@RestController
@RequestMapping("/api/v1/university/professors")
@RequiredArgsConstructor
public class ProfessorController {

	private final ProfessorService professorService;
}
