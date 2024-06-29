package ru.scarlet.company.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.company.dtos.DepartmentRequest;
import ru.scarlet.company.dtos.DepartmentResponse;
import ru.scarlet.company.services.DepartmentService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/university/departments")
public class DepartmentController extends BasicController{

	private final DepartmentService departmentService;

	@PostMapping("/")
	public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest, HttpServletRequest request){
		DepartmentResponse department = departmentService.createDepartment(departmentRequest);
		return ResponseEntity.created(URI.create(request.getRequestURI())).body(department);
	}

	@PostMapping("/{departmentOid}/professor/{professorOid}")
	public ResponseEntity<?> addProfessor(@PathVariable Integer departmentOid, @PathVariable Integer professorOid){
		departmentService.addProfessorToDepartment(departmentOid, professorOid);
		return ResponseEntity.noContent().build();
	}
}
