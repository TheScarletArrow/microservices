package ru.scarlet.company.services.impl;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanResponse;
import ru.scarlet.company.dtos.FacultyRequest;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.excpetions.NotFound.DeanNotFoundException;
import ru.scarlet.company.excpetions.NotFound.FacultyNotFoundException;
import ru.scarlet.company.mappers.FacultyMapper;
import ru.scarlet.company.repository.DeanRepository;
import ru.scarlet.company.repository.FacultyRepository;
import ru.scarlet.company.services.DeanService;
import ru.scarlet.company.services.FacultyService;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

	private final FacultyRepository facultyRepository;

	private final DeanRepository deanRepository;

	private final DeanService deanService;

	private final FacultyMapper facultyMapper;

	@Override
	public FacultyResponse createFaculty(FacultyRequest facultyRequest) {
		Faculty faculty = new Faculty();
		faculty.setName(facultyRequest.getName());
		faculty.setShortName(facultyRequest.getShortName());
		faculty.setDean(deanRepository.findById(facultyRequest.getDeanId()).orElseThrow(()->new DeanNotFoundException("Dean not found")));
		Faculty save = facultyRepository.save(faculty);
		return new FacultyResponse(save.getName(), save.getShortName(), new DeanResponse(save.getDean().getFirstName(), save.getDean().getLastName(), save.getDean().getPatronymic()));
	}

	@Override
	public List<Faculty> getAll() {
		return facultyRepository.findAll();

	}

	@Override
	public Faculty getById(Integer id) {
		return facultyRepository.findById(id).orElseThrow(()->new FacultyNotFoundException("Faculty not found"));
	}

	@Override
	@Transactional
	public void setDeanAndFac(Integer facId, Integer id) {
		Dean dean = deanService.getDeanById(id);
		Faculty faculty = getById(facId);
		dean.setFaculty(faculty);
		faculty.setDean(dean);
	}
}
