package ru.scarlet.company.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.FacultyGetAll;
import ru.scarlet.company.services.DeanService;
import ru.scarlet.company.services.FacultyService;

@Controller
@RequiredArgsConstructor
public class QueryController {

    private final DeanService deanService;

    private final FacultyService facultyService;
    @QueryMapping
    List<DeanGetResponse> deans(){
        return deanService.getAll();
    }

    @QueryMapping
    List<FacultyGetAll> faculties(){
        return facultyService.getAll();
    }
}
