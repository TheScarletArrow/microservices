package ru.scarlet.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Expertise;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {
	Expertise findByName(String name);

}