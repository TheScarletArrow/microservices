package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;
import ru.scarlet.company.mappers.ExpertiseMapping;
import ru.scarlet.company.repository.ExpertiseRepository;
import ru.scarlet.company.services.ExpertiseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertiseServiceImpl implements ExpertiseService {

	private final ExpertiseRepository expertiseRepository;
	private final ExpertiseMapping expertiseMapping;

	@Override
	public Expertise create(ExpertiseDto expertiseDto) {
		Expertise expertise = expertiseMapping.toEntity(expertiseDto);
		return expertiseRepository.save(expertise);
	}

	@Override
	public List<Expertise> getAll() {
		return expertiseRepository.findAll();
	}
}
