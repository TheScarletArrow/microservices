package ru.scarlet.company.mappers;

import org.mapstruct.Mapper;
import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;

@Mapper(componentModel = "spring")
public interface ExpertiseMapping {

	ExpertiseDto toDto(Expertise expertise);
}
