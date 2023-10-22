package ru.scarlet.company.services.impl;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.dtos.ProfessorDtoResponse;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.excpetions.NotFound.ExpertiseNotFoundException;
import ru.scarlet.company.excpetions.NotFound.ProfessorNotFoundException;
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
		professor.setExpertise(expertiseRepository.findById(dto.getExpertise()).orElseThrow(()->new ExpertiseNotFoundException("Expertise not found")));
		professor.setTeachingCourses(new ArrayList<>());
		return professorRepository.save(professor);
	}

	@Override
	public List<ProfessorDtoResponse> getAllByDepartmentId(String departmentId) {
		return professorRepository.findByDepartmentShortName(departmentId).stream().map(professorsMapper::toResponse).toList();
	}

	@Override
	public List<Professor> getAllByDepartmentIdE(Long departmentId) {
		return professorRepository.findByDepartmentOid(departmentId);
	}

	@Override
	public Professor getById(Integer professorId) {
		return professorRepository.findById(professorId).orElseThrow(()->new ProfessorNotFoundException("Professor not found"));
	}

	@Override
	@Transactional
	public Professor setProperties(Integer professorId, String email, String phone) {
		Professor byId = getById(professorId);
		if (email!=null) byId.setEmail(email);
		if (phone!=null) byId.setPhone(phone);
		return byId;
	}
}
