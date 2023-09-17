package ru.scarlet.company.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.DepartmentDtoCourse;
import ru.scarlet.company.dtos.DepartmentShortResponse;
import ru.scarlet.company.entities.Department;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {

	DepartmentDtoCourse toDto(Department department);

	DepartmentShortResponse toShortResponse(Department department);

	List<DepartmentShortResponse> toListShortResponse(List<Department> departmentList);
}
