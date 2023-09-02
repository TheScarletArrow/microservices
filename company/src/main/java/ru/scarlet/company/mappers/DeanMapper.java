package ru.scarlet.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanResponse;
import ru.scarlet.company.entities.Dean;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {FacultyMapper.class})
public interface DeanMapper {

	DeanResponse toResponse(Dean dean);

	DeanGetResponse toGetResponse(Dean dean);
}
