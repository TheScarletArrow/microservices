package ru.scarlet.company.services;

import java.util.List;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.dtos.ProfessorDtoResponse;
import ru.scarlet.company.entities.Professor;

public interface ProfessorService {
	Professor add(ProfessorDtoRequest dto);

    List<ProfessorDtoResponse> getAllByDepartmentId(String departmentId);
}
