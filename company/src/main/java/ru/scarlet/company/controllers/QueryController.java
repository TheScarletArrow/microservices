package ru.scarlet.company.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.services.DeanService;

@Controller
@RequiredArgsConstructor
public class QueryController {

    private final DeanService deanService;
    @QueryMapping
    List<DeanGetResponse> deans(){
        return deanService.getAll();
    }

}
