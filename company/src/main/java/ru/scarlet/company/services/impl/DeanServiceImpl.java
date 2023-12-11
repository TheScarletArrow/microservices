package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.dtos.FacultyDeanGetResponse;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.excpetions.NotFound.DeanNotFoundException;
import ru.scarlet.company.mappers.DeanMapper;
import ru.scarlet.company.repository.DeanRepository;
import ru.scarlet.company.repository.FacultyRepository;
import ru.scarlet.company.services.DeanService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeanServiceImpl implements DeanService {

	private final DeanRepository deanRepository;
	private final DeanMapper deanMapper;
	private final FacultyRepository facultyRepository;

	@Override
	public Dean createDean(DeanRequest deanRequest) {
		Dean dean = new Dean();
		dean.setFirstName(deanRequest.getFirstName());
		dean.setLastName(deanRequest.getLastName());
		dean.setPatronymic(deanRequest.getPatronymic());
		dean.setTeachingCourses(new ArrayList<>());
		return deanRepository.save(dean);
	}

	@Override
	public List<DeanGetResponse> getAll() {
		List<Dean> all = deanRepository.findAll();
		List<DeanGetResponse> deanResponses = new ArrayList<>();
		for (Dean dean : all) {
			Faculty byDeanOid = facultyRepository.findByDeanOid(dean.getOid());
			FacultyDeanGetResponse facultyDeanResponse = new FacultyDeanGetResponse(byDeanOid.getName(), byDeanOid.getShortName());
			DeanGetResponse deanResponse = new DeanGetResponse(dean.getFirstName(), dean.getLastName(), dean.getPatronymic(), facultyDeanResponse);
			deanResponses.add(deanResponse);
		}
		return deanResponses;
	}

	@Override
	public List<Dean> getAllEntity() {

		return deanRepository.findAll();
	}

	@Override
	public Dean getDeanById(Integer id) {
		return deanRepository.findById(id).orElseThrow(()->new DeanNotFoundException("Dean not found with id "+id));
	}

	@Override
	public DeanGetResponse getDeanDtoById(Integer id) {
		DeanGetResponse getResponse = deanMapper.toGetResponse(getDeanById(id));
		return getResponse;
	}
}
