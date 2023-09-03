package ru.scarlet.company.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.ProfessorDtoCourse;
import ru.scarlet.company.dtos.ProfessorDtoRequest;
import ru.scarlet.company.entities.Professor;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ExpertiseMapping.class})
public interface ProfessorsMapper {

	ProfessorDtoCourse toDto(Professor professor);

	Professor toEntity(ProfessorDtoRequest professorDtoCourse);

	List<ProfessorDtoCourse> toDtoList(List<Professor> taughtByProfessors);
}
