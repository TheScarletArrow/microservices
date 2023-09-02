package ru.scarlet.company.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query(value = "select c from Course c where c.department = (select d from Department d where d.shortName=:departmentShortName)")
	List<Course> findByDepartmentShortName(String departmentShortName);



}