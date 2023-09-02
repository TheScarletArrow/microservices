package ru.scarlet.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.DepartmentDtoCourse;
import ru.scarlet.company.entities.Department;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {

	DepartmentDtoCourse toDto(Department department);
}
