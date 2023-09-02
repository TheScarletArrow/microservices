package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanResponse;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.repository.DeanRepository;
import ru.scarlet.company.repository.FacultyRepository;
import ru.scarlet.company.services.FacultyService;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

	private final FacultyRepository facultyRepository;

	private final DeanRepository deanRepository;
	@Override
	public FacultyResponse createFaculty(FacultyRequest facultyRequest) {
		Faculty faculty = new Faculty();
		faculty.setName(facultyRequest.getName());
		faculty.setShortName(facultyRequest.getShortName());
		faculty.setDean(deanRepository.findById(facultyRequest.getDeanId()).orElseThrow(()->new RuntimeException("")));
		Faculty save = facultyRepository.save(faculty);
		return new FacultyResponse(save.getName(), save.getShortName(), new DeanResponse(save.getDean().getFirstName(), save.getDean().getLastName(), save.getDean().getPatronymic()));
	}
}
