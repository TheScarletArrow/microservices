package ru.scarlet.company.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.dtos.ProfessorDtoResponse;
import ru.scarlet.company.entities.Expertise;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.excpetions.NotFound.ExpertiseNotFoundException;
import ru.scarlet.company.excpetions.NotFound.ProfessorNotFoundException;
import ru.scarlet.company.mappers.ProfessorsMapper;
import ru.scarlet.company.repository.ExpertiseRepository;
import ru.scarlet.company.repository.ProfessorRepository;
import ru.scarlet.company.services.ProfessorService;

import java.util.ArrayList;
import java.util.List;

import static ru.scarlet.company.configs.RabbitConfigurationKt.MAIL_QUEUE;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
	private final ProfessorRepository professorRepository;
	private final ProfessorsMapper professorsMapper;
	private final ExpertiseRepository expertiseRepository;
	private final RabbitTemplate rabbitTemplate;
	@Override
	@CachePut(cacheNames = "professor", key = "#result.oid")
	public Professor add(ProfessorDtoRequest dto) {
		Professor professor = professorsMapper.toEntity(dto);
		Expertise expertiseNotFound = expertiseRepository.findById(dto.getExpertiseId()).orElseThrow(() -> new ExpertiseNotFoundException("Expertise not found"));
		professor.setExpertiseId(expertiseNotFound.getId());
		professor.setTeachingCourses(new ArrayList<>());
		rabbitTemplate.convertAndSend(MAIL_QUEUE);
        return professorRepository.save(professor);
	}

	@Override
	@Cacheable(cacheNames = "professorsDto", key = "#departmentId")
	public List<ProfessorDtoResponse> getAllByDepartmentId(String departmentId) {
		return professorRepository.findByDepartmentShortName(departmentId).stream().map(professorsMapper::toResponse).toList();
	}

	@Override
	@Cacheable(cacheNames = "professors", key = "#departmentId")
	public List<Professor> getAllByDepartmentIdE(Long departmentId) {
		return professorRepository.findByDepartmentOid(departmentId);
	}

	@Override
	@Cacheable(cacheNames = "professor", key = "#professorId")
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

	@Override
	public List<Professor> getAllByCourseId(Integer courseId) {
		return professorRepository.findAllByCourseId(courseId);
	}
}
