package ru.scarlet.company.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.*;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Department;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.enums.CourseActive;
import ru.scarlet.company.excpetions.NotFound.CourseNotFoundException;
import ru.scarlet.company.excpetions.NotFound.DepartmentNotFoundException;
import ru.scarlet.company.excpetions.NotFound.ProfessorNotFoundException;
import ru.scarlet.company.excpetions.alreadyExists.CourseAlreadyExistsException;
import ru.scarlet.company.mappers.CourseMapper;
import ru.scarlet.company.mappers.ProfessorsMapper;
import ru.scarlet.company.repository.CourseRepository;
import ru.scarlet.company.repository.DepartmentRepository;
import ru.scarlet.company.repository.ProfessorRepository;
import ru.scarlet.company.services.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.scarlet.company.configs.RabbitConfigurationKt.NEW_MAIL_ROUTING_KEY;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {


	private final CourseRepository courseRepository;

	private final DepartmentRepository departmentRepository;

	private final ProfessorRepository professorRepository;

	private final CourseMapper courseMapper;
	private final ProfessorsMapper professorsMapper;

	private final RabbitTemplate rabbitTemplate;

	@Override
	public List<CourseResponse> getCoursesByDepartmentId(String departmentId, Integer page, Integer perPage) {
		Pageable pageable = PageRequest.of(page, perPage);
		Page<Course> courseByDepartmentShortName = courseRepository.getCourseByDepartmentShortName(departmentId, pageable);
        return courseMapper.toDto(courseByDepartmentShortName.getContent());
	}

	@Override
	public List<Course> getCoursesByDepartment(String departmentId, Integer page, Integer perPage) {
		Pageable pageable = PageRequest.of(page, perPage);
		Page<Course> courseByDepartmentShortName = courseRepository.getCourseByDepartmentShortName(departmentId, pageable);
		return courseByDepartmentShortName.getContent();

	}

	@Override
	public CourseResponse add(CourseRequest courseRequest) {
		Course course = new Course();
		course.setCourseCode(courseRequest.getCourseCode());
		course.setCourseName(courseRequest.getCourseName());
		course.setDepartment(departmentRepository.findById(courseRequest.getDepartmentOid()).orElseThrow(()->new DepartmentNotFoundException("Department not found")));
		if(!courseRequest.getProfessors().isEmpty()) {
			List<Professor> allById = professorRepository.findAllById(courseRequest.getProfessors());
			course.setTaughtByProfessors(allById);
		} else course.setTaughtByProfessors(new ArrayList<>());
		if (courseRepository.existsByCourseCode(courseRequest.getCourseCode())) throw new CourseAlreadyExistsException("Course "+ courseRequest.getCourseCode());
		Course save = courseRepository.save(course);
		return new CourseResponse(save.getOid(),courseRequest.getCourseCode(), courseRequest.getCourseName(),
				new DepartmentDtoCourse(course.getDepartment().getShortName(), course.getDepartment().getName()), save.getTaughtByProfessors().stream().map(professorsMapper::toDto).collect(Collectors.toList()));
	}

	@Override
	@Transactional
	public void addCourseByDepartmentId(Integer departmentId, Integer courseId) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(()->new DepartmentNotFoundException(("Department not found")));

		Course course = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found"));
		department.getTeachingCourses().add(course);
		course.setDepartment(department);
	}

	@Override
	public CourseResponse getCourseById(Integer courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course " + courseId + " not found"));
		return courseMapper.toDto(course);
	}

	@Override
	@Transactional
	public void addProfessor(Integer courseId, Integer professorId) {
		Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ProfessorNotFoundException("Professor " + professorId + " not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course " + courseId + " not found"));
		course.getTaughtByProfessors().add(professor);
		professor.getTeachingCourses().add(course);

		ProfessorContactDetails contactDetails = new ProfessorContactDetails(professor.getPhone(), professor.getEnableNotifyByPhone(), professor.getEnableNotifyByMail(), professor.getEmail(), new CourseShort(course.getCourseName(), course.getCourseCode()), MailTopic.ADDED);

		rabbitTemplate.convertAndSend(NEW_MAIL_ROUTING_KEY, (contactDetails));
	}

	@Override
	public List<Course> getCoursesByProfessor(Integer professorId) {
		return courseRepository.getCoursesByProfessorId(professorId);
	}

	@Override
	public Course getCourseByIdE(Integer courseId) {
		return courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found"));
	}

	@Override
	public Boolean courseCodeExists(String courseCode) {
		return courseRepository.existsByCourseCode(courseCode);
	}

	@Override
	public void deleteCourseByOid(Integer oid) {
		courseRepository.deleteById(oid);
	}

	@Override
	@Transactional
	public void setIsActive(Integer courseId, CourseActive active) {
		var courseByIdE = getCourseByIdE(courseId);
		courseByIdE.setCourseActive(active);
	}

	@Override
	@Transactional
	public void deactivateCourse(Integer courseId) {
		var courseByIdE = getCourseByIdE(courseId);
		courseByIdE.setCourseActive(CourseActive.DEACTIVATED);

	}

	@Override
	public void removeProfessorFromCourse(Integer courseId, Integer professorId) {
		Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new ProfessorNotFoundException("Professor " + professorId + " not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course " + courseId + " not found"));
		course.getTaughtByProfessors().remove(professor);
		professor.getTeachingCourses().remove(course);

		ProfessorContactDetails contactDetails = new ProfessorContactDetails(professor.getPhone(), professor.getEnableNotifyByPhone(), professor.getEnableNotifyByMail(), professor.getEmail(), new CourseShort(course.getCourseName(), course.getCourseCode()), MailTopic.REMOVED);

		rabbitTemplate.convertAndSend(NEW_MAIL_ROUTING_KEY, (contactDetails));
	}
}
