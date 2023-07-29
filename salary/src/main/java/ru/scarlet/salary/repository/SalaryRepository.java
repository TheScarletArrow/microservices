package ru.scarlet.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scarlet.salary.entities.Salary;

import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {
}
