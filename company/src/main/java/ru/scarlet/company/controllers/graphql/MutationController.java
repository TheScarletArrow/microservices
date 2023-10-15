package ru.scarlet.company.controllers.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.services.CourseService;
import ru.scarlet.company.services.ProfessorService;

@Controller
@RequiredArgsConstructor
public class MutationController {

    private final ProfessorService professorService;
    private final CourseService courseService;

    @MutationMapping
    public Professor updateProfessorContactInfo(@Argument Integer id, @Argument String email, @Argument String phone){
       return  professorService.setProperties(id, email, phone);
    }

    @MutationMapping
    public Course addProfessorToCourse(@Argument Integer professorId, @Argument Integer courseId){
        courseService.addProfessor(courseId, professorId);

        return courseService.getCourseByIdE(courseId);
    }
}
