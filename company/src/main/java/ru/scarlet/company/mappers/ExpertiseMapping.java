package ru.scarlet.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertiseMapping {

	ExpertiseDto toDto(Expertise expertise);

	Expertise toEntity(ExpertiseDto expertiseDto);
}
