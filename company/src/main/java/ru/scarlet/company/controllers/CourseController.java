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
import org.springframework.web.bind.annotation.RequestParam;
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
	private ResponseEntity<?> getCoursesForDepartment(@PathVariable String departmentId,
	                                                  @RequestParam(defaultValue = "0") Integer page,
	                                                  @RequestParam(defaultValue = "10") Integer perPage) {
		List<CourseResponse> courseResponseList = courseService.getCoursesByDepartmentId(departmentId, page, perPage);
		return ResponseEntity.ok().body(courseResponseList);
	}

	@PostMapping("/department/{departmentId}/{courseId}")
	private ResponseEntity<Void> addCourseForDepartment(@PathVariable String departmentId, @PathVariable Integer courseId) {
		courseService.addCourseByDepartmentId(departmentId, courseId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/")
	public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest courseRequest, HttpServletRequest request) {
		CourseResponse add = courseService.add(courseRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(add);
	}

	@GetMapping("/{courseId}")
	private ResponseEntity<CourseResponse> getCourseById(@PathVariable(required = false) Integer courseId) {
		CourseResponse response = courseService.getCourseById(courseId);
		return ResponseEntity.ok(response);
	}
}
