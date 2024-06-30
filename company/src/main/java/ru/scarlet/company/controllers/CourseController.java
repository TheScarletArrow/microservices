package ru.scarlet.company.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.company.dtos.CourseRequest;
import ru.scarlet.company.dtos.CourseResponse;
import ru.scarlet.company.enums.CourseActive;
import ru.scarlet.company.services.CourseService;
import ru.scarlet.company.services.StudentService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/university/courses")
public class CourseController extends BasicController{
	private final CourseService courseService;
	private final StudentService studentService;

	@Autowired // Add this annotation
	public CourseController(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
        this.studentService = studentService;
    }

	@GetMapping("/department/{departmentId}")
	private ResponseEntity<?> getCoursesForDepartment(@PathVariable String departmentId,
	                                                  @RequestParam(defaultValue = "0") Integer page,
	                                                  @RequestParam(defaultValue = "10") Integer perPage) {
		List<CourseResponse> courseResponseList = courseService.getCoursesByDepartmentId(departmentId, page, perPage);
		return ResponseEntity.ok().body(courseResponseList);
	}

	@PostMapping("/department/{departmentId}/{courseId}")
	private ResponseEntity<Void> addCourseForDepartment(@PathVariable Integer departmentId, @PathVariable Integer courseId) {
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

	@PostMapping("/{courseId}/professor/{professorId}")
	public ResponseEntity<Void> addProfessorToCourse(@PathVariable Integer professorId, @PathVariable Integer courseId) throws JsonProcessingException {
		courseService.addProfessor(courseId, professorId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/courseCode/{courseCode}/exists")
	public ResponseEntity<Boolean> courseCodeExists(@PathVariable String courseCode){
		return ResponseEntity.ok(courseService.courseCodeExists(courseCode));
	}

	@PatchMapping("/{courseId}/active/{active}")
	public ResponseEntity<?> setIsActive(@PathVariable CourseActive active, @PathVariable Integer courseId){
		courseService.setIsActive(courseId, active);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deactivateCourse(@PathVariable Integer courseId) {
		courseService.deactivateCourse(courseId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/list")
	public ResponseEntity<List<CourseResponse>> getCourses(@RequestHeader(name = AUTHORIZATION) String authToken) {
		String username = getUsernameFromToken(authToken);

		List<CourseResponse> response = courseService.list(username);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{courseId}/enroll")
	public ResponseEntity<Void> enroll(@RequestHeader(name = AUTHORIZATION) String authToken, @PathVariable Integer courseId){
		String username = getUsernameFromToken(authToken);

		studentService.enrollToCourse(username, courseId);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{courseId}/leave")
	public ResponseEntity<Void> leave(@RequestHeader(name = AUTHORIZATION) String authToken, @PathVariable Integer courseId){
		String username = getUsernameFromToken(authToken);

		studentService.leaveCourse(username, courseId);

		return ResponseEntity.noContent().build();
	}


}
