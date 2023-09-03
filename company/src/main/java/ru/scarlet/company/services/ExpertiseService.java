package ru.scarlet.company.services;


import ru.scarlet.company.dtos.ExpertiseDto;
import ru.scarlet.company.entities.Expertise;

public interface ExpertiseService {

	Expertise create(ExpertiseDto expertiseDto);
}
