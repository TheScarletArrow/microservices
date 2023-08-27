package ru.scarlet.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}