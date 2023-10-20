package ru.scarlet.company.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.ProfessorContactDetails;
import ru.scarlet.company.dtos.ProfessorDtoCourse;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.dtos.ProfessorDtoResponse;
import ru.scarlet.company.entities.Professor;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ExpertiseMapping.class})
public interface ProfessorsMapper {

	ProfessorDtoCourse toDto(Professor professor);

	@Mapping(target = "expertise", source = "expertise", ignore = true)
	Professor toEntity(ProfessorDtoRequest professorDtoCourse);

	List<ProfessorDtoCourse> toDtoList(List<Professor> taughtByProfessors);

	ProfessorContactDetails toContactDetails(Professor professor);

	ProfessorDtoResponse toResponse(Professor professor);
	List<ProfessorDtoResponse> toResponse(List<Professor> professor);

}
