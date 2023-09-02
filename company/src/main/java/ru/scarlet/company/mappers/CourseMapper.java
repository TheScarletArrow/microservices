package ru.scarlet.company.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.scarlet.company.dtos.CourseResponse;
import ru.scarlet.company.entities.Course;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {DepartmentMapper.class, ProfessorsMapper.class})
public interface CourseMapper {


	CourseResponse toDto(Course course);

	List<CourseResponse> toDto(List<Course> courses);

}
