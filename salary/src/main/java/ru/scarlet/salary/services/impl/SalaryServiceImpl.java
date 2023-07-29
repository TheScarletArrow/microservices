package ru.scarlet.salary.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scarlet.salary.entities.Salary;
import ru.scarlet.salary.repository.SalaryRepository;
import ru.scarlet.salary.services.SalaryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

	private final SalaryRepository salaryRepository;

	@Override
	public List<Salary> getSalary() {
		return salaryRepository.findAll();
	}
}
