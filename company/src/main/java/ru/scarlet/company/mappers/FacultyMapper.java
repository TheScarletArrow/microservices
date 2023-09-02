package ru.scarlet.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.FacultyDeanGetResponse;
import ru.scarlet.company.entities.Faculty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FacultyMapper {

	FacultyDeanGetResponse toDto(Faculty faculty);
}
