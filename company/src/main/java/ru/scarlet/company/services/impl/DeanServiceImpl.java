package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.repository.DeanRepository;
import ru.scarlet.company.services.DeanService;

@Service
@RequiredArgsConstructor
public class DeanServiceImpl implements DeanService {

	private final DeanRepository deanRepository;
	@Override
	public Dean createDean(DeanRequest deanRequest) {
		Dean dean = new Dean();
		dean.setFirstName(deanRequest.getFirstName());
		dean.setLastName(deanRequest.getLastName());
		dean.setPatronymic(deanRequest.getPatronymic());

		return deanRepository.save(dean);
	}
}
