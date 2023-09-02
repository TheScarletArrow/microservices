package ru.scarlet.company.services;

import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;

public interface FacultyService {
	FacultyResponse createFaculty(FacultyRequest facultyRequest);
}
