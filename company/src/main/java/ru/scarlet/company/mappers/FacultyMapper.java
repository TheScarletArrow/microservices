package ru.scarlet.company.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.FacultyDeanGetResponse;
import ru.scarlet.company.dtos.FacultyGetAll;
import ru.scarlet.company.entities.Faculty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = DepartmentMapper.class)
public interface FacultyMapper {

	FacultyDeanGetResponse toDto(Faculty faculty);

	FacultyGetAll toDetAll(Faculty faculty);

	List<FacultyGetAll> toListGetAll(List<Faculty> facultyList);
}
