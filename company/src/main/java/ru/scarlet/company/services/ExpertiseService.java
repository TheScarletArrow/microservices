package ru.scarlet.company.services;


import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;

import java.util.List;

public interface ExpertiseService {

	Expertise create(ExpertiseDto expertiseDto);

    List<Expertise> getAll();
}
