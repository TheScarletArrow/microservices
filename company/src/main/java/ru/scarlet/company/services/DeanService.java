package ru.scarlet.company.services;

import java.util.List;
import ru.scarlet.company.dtos.DeanGetResponse;
import ru.scarlet.company.dtos.DeanRequest;
import ru.scarlet.company.entities.Dean;

public interface DeanService {
	Dean createDean(DeanRequest deanRequest);

	List<DeanGetResponse> getAll();

	List<Dean> getAllEntity();

	Dean getDeanById(Integer id);
}
