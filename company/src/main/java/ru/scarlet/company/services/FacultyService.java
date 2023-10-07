package ru.scarlet.company.services;

import java.util.List;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.entities.Faculty;

public interface FacultyService {
	FacultyResponse createFaculty(FacultyRequest facultyRequest);

    List<Faculty> getAll();

    Faculty getById(Integer id);

    void setDeanAndFac(Integer facId, Integer id);
}
