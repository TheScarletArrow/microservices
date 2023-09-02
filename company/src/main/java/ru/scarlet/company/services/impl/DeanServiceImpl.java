package ru.scarlet.company.services.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.dtos.FacultyDeanGetResponse;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.mappers.DeanMapper;
import ru.scarlet.company.repository.DeanRepository;
import ru.scarlet.company.repository.FacultyRepository;
import ru.scarlet.company.services.DeanService;

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

		return deanRepository.save(dean);
	}

	@Override
	public List<DeanGetResponse> getAll() {
		List<Dean> all = deanRepository.findAll();
		List<DeanGetResponse> deanResponses = new ArrayList<>();
		for (Dean dean : all) {
			Faculty byDeanOid = facultyRepository.findByDean_Oid(dean.getOid());
			FacultyDeanGetResponse facultyDeanResponse = new FacultyDeanGetResponse(byDeanOid.getName(), byDeanOid.getShortName());
			DeanGetResponse deanResponse = new DeanGetResponse(dean.getFirstName(), dean.getLastName(), dean.getPatronymic(), facultyDeanResponse);
			deanResponses.add(deanResponse);
		}
		return deanResponses;
	}
}
