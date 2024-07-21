package ru.scarlet.company.services;

import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;

import java.util.List;

public interface DeanService {
	Dean createDean(DeanRequest deanRequest);

	List<DeanGetResponse> getAll();

	List<Dean> getAllEntity();

	Dean getDeanById(Integer id);

	DeanGetResponse getDeanDtoById(Integer id);


	DeanGetResponse modifyDean(Integer id, DeanRequest deanRequest);
}
