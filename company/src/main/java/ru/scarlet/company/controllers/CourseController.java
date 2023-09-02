package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.company.dtos.CourseRequest;
import ru.scarlet.company.dtos.CourseResponse;
import ru.scarlet.company.services.CourseService;

@RestController
@RequestMapping("/api/v1/university/courses")
public class CourseController {
	private final CourseService courseService;

	@Autowired // Add this annotation
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	@GetMapping("/department/{departmentId}")
	private ResponseEntity<?> getCoursesForDepartment(@PathVariable String departmentId){
		List<CourseResponse> courseResponseList = courseService.getCoursesByDepartmentId(departmentId);
		return ResponseEntity.ok().body(courseResponseList);
	}

	@PostMapping("/")
	public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest courseRequest, HttpServletRequest request){
		CourseResponse add = courseService.add(courseRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(add);
	}
}
