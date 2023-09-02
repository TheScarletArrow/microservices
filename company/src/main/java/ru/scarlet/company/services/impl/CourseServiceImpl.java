package ru.scarlet.company.services.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.CourseRequest;
import ru.scarlet.company.dtos.CourseResponse;
import ru.scarlet.company.dtos.DepartmentDtoCourse;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Department;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.mappers.CourseMapper;
import ru.scarlet.company.mappers.ProfessorsMapper;
import ru.scarlet.company.repository.CourseRepository;
import ru.scarlet.company.repository.DepartmentRepository;
import ru.scarlet.company.repository.ProfessorRepository;
import ru.scarlet.company.services.CourseService;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	private final DepartmentRepository departmentRepository;

	private final ProfessorRepository professorRepository;

	private final CourseMapper courseMapper;
	private final ProfessorsMapper professorsMapper;

	@Override
	public List<CourseResponse> getCoursesByDepartmentId(String departmentId) {
		List<Course> byDepartmentShortName = courseRepository.findByDepartmentShortName(departmentId);
		List<CourseResponse> dto = courseMapper.toDto(byDepartmentShortName);
		return dto;
	}

	@Override
	public CourseResponse add(CourseRequest courseRequest) {
		Course course = new Course();
		course.setCourseCode(courseRequest.getCourseCode());
		course.setCourseName(courseRequest.getCourseName());
		course.setDepartment(departmentRepository.findById(courseRequest.getDepartmentOid()).orElseThrow(()->new RuntimeException("")));
		List<Professor> allById = professorRepository.findAllById(courseRequest.getProfessors());
		course.setTaughtByProfessors(allById);
		Course save = courseRepository.save(course);
		return new CourseResponse(courseRequest.getCourseCode(), courseRequest.getCourseName(),
				new DepartmentDtoCourse(course.getDepartment().getShortName(), course.getDepartment().getName()), allById.stream().map(professorsMapper::toDto).collect(Collectors.toList()));
	}

	@Override
	@Transactional
	public void addCourseByDepartmentId(String departmentId, Integer courseId) {
		Department department = departmentRepository.findByShortName(departmentId);

		Course course = courseRepository.findById(courseId).orElseThrow(()->new RuntimeException(""));
		department.getTeachingCorses().add(course);
		course.setDepartment(department);
	}
}
