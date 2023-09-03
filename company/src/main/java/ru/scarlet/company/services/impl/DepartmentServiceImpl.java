package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.company.dtos.DeanResponse;
import ru.scarlet.company.dtos.DepartmentRequest;
import ru.scarlet.company.dtos.DepartmentResponse;
import ru.scarlet.company.dtos.FacultyResponse;
import ru.scarlet.company.entities.Department;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.excpetions.NotFound.FacultyNotFoundException;
import ru.scarlet.company.repository.DepartmentRepository;
import ru.scarlet.company.repository.FacultyRepository;
import ru.scarlet.company.services.DepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository departmentRepository;

	private final FacultyRepository facultyRepository;
	@Override
	public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
		Department department = new Department();
		Faculty faculty = facultyRepository.findById(departmentRequest.getFacultyId()).orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));
		department.setFaculty(faculty);
		department.setName(departmentRequest.getName());
		department.setShortName(departmentRequest.getShortName());
		department.setName(departmentRequest.getName());

		Department save = departmentRepository.save(department);
		return new DepartmentResponse(save.getShortName(), save.getName(), new FacultyResponse(faculty.getName(), faculty.getShortName(), new DeanResponse(faculty.getDean().getFirstName(), faculty.getDean().getLastName(), faculty.getDean().getPatronymic())));
	}
}
