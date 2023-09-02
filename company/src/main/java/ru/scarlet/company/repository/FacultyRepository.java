package ru.scarlet.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	Faculty findByDean_Oid(Integer oid);
}