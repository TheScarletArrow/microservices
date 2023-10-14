package ru.scarlet.company.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Dean;
import ru.scarlet.company.entities.Department;
import ru.scarlet.company.entities.Faculty;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.services.CourseService;
import ru.scarlet.company.services.DeanService;
import ru.scarlet.company.services.DepartmentService;
import ru.scarlet.company.services.FacultyService;
import ru.scarlet.company.services.ProfessorService;

@Controller
@RequiredArgsConstructor
public class QueryController {

    private final DeanService deanService;

    private final FacultyService facultyService;

    private final DepartmentService departmentService;

    private final ProfessorService professorService;

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
    List<Professor> professorsByDep(@Argument String departmentId){return professorService.getAllByDepartmentIdE(departmentId);}

    @QueryMapping
    List<Course> coursesByDep(@Argument String department, @Argument Integer page,  @Argument Integer perPage){
        return courseService.getCoursesByDepartment(department, page, perPage);
    }

}
