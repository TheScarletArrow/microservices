package ru.scarlet.company.services;

import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;

public interface DeanService {
	Dean createDean(DeanRequest deanRequest);
}
