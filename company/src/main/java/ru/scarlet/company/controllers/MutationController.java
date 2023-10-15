package ru.scarlet.company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.entities.Professor;
import ru.scarlet.company.services.ProfessorService;

@Controller
@RequiredArgsConstructor
public class MutationController {

    private final ProfessorService professorService;

    @MutationMapping
    public Professor updateProfessorContactInfo(@Argument Integer id, @Argument String email, @Argument String phone){
       return  professorService.setProperties(id, email, phone);
    }
}
