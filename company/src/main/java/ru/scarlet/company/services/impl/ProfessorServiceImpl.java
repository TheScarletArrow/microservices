package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.mappers.ProfessorsMapper;
import ru.scarlet.company.repository.ExpertiseRepository;
import ru.scarlet.company.repository.ProfessorRepository;
import ru.scarlet.company.services.ProfessorService;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
	private final ProfessorRepository professorRepository;
	private final ProfessorsMapper professorsMapper;
	private final ExpertiseRepository expertiseRepository;
	@Override
	public Professor add(ProfessorDtoRequest dto) {
		Professor professor = professorsMapper.toEntity(dto);
		professor.setExpertise(expertiseRepository.findByName(dto.getExpertise().getName()));
		return professorRepository.save(professor);
	}
}
