package ru.scarlet.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Department;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	Department findByShortName(String shortName);

}