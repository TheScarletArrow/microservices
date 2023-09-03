package ru.scarlet.company.services;

import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.entities.Professor;

public interface ProfessorService {
	Professor add(ProfessorDtoRequest dto);
}
