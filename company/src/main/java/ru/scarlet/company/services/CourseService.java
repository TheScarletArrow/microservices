package ru.scarlet.company.services;

import java.util.List;
import ru.scarlet.company.dtos.CourseRequest;
import ru.scarlet.company.dtos.CourseResponse;

public interface CourseService {
	List<CourseResponse> getCoursesByDepartmentId(String departmentId);

	CourseResponse add(CourseRequest courseRequest);

	void addCourseByDepartmentId(String departmentId, Integer courseRequest);

	CourseResponse getCourseById(Integer courseId);
}
