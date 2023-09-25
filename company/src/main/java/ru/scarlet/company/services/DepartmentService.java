package ru.scarlet.company.services;

import ru.scarlet.company.dtos.DepartmentRequest;
import ru.scarlet.company.dtos.DepartmentResponse;
import ru.scarlet.company.entities.Department;

import java.util.List;

public interface DepartmentService {
	DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    List<Department> getAll();
}
