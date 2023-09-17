package ru.scarlet.company.services;

import java.util.List;
import ru.scarlet.company.dtos.FacultyGetAll;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;

public interface FacultyService {
	FacultyResponse createFaculty(FacultyRequest facultyRequest);

    List<FacultyGetAll> getAll();
}
