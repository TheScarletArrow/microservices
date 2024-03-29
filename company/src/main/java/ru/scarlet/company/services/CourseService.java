package ru.scarlet.company.services;

import ru.scarlet.company.dtos.CourseRequest;
import ru.scarlet.company.dtos.CourseResponse;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.enums.CourseActive;

import java.util.List;

public interface CourseService {
	List<CourseResponse> getCoursesByDepartmentId(String departmentId, Integer page, Integer perPage);

	List<Course> getCoursesByDepartment(String departmentId, Integer page, Integer perPage);
	CourseResponse add(CourseRequest courseRequest);

	void addCourseByDepartmentId(Integer departmentId, Integer courseRequest);

	CourseResponse getCourseById(Integer courseId);

	void addProfessor(Integer courseId, Integer professorId);

    List<Course> getCoursesByProfessor(Integer professorId);

	Course getCourseByIdE(Integer courseId);

    Boolean courseCodeExists(String courseCode);

	void deleteCourseByOid(Integer oid);

	void setIsActive(Integer courseId, CourseActive active);

	void deactivateCourse(Integer courseId);

    void removeProfessorFromCourse(Integer courseId, Integer professorId);
}
