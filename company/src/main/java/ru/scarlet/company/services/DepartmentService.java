package ru.scarlet.company.services;

import ru.scarlet.company.dtos.DepartmentRequest;
import ru.scarlet.company.dtos.DepartmentResponse;

public interface DepartmentService {
	DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
}
