package ru.scarlet.company.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.entities.*;
import ru.scarlet.company.services.*;

@Controller
@RequiredArgsConstructor
public class QueryController {

    private final DeanService deanService;

    private final FacultyService facultyService;

    private final DepartmentService departmentService;

    private final ProfessorService professorService;

    private final ExpertiseService expertiseService;
    private final CourseService courseService;
    @QueryMapping
    List<Dean> deans(){
        return deanService.getAllEntity();
    }

    @QueryMapping
    List<Faculty> faculties(){
        return facultyService.getAll();
    }

    @QueryMapping
    List<Department> departments(){return departmentService.getAll();}

    @QueryMapping
    List<Professor> professors(@Argument String departmentId){return professorService.getAllByDepartmentIdE(departmentId);}

    @QueryMapping
    List<Course> coursesByDep(@Argument String department, @Argument Integer page,  @Argument Integer perPage){
        return courseService.getCoursesByDepartment(department, page, perPage);
    }
}
