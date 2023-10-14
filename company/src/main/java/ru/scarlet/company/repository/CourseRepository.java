package ru.scarlet.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.scarlet.company.entities.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

//	@Query(value = "select c from Course c where c.department = (select d from Department d where d.shortName=:departmentShortName)")
//	List<Course> findByDepartmentShortName(String departmentShortName, Integer page, Integer perPage);

	@Query(value = "select c from Course c where c.department = (select d from Department d where d.shortName=:departmentShortName)")
	Page<Course> getCourseByDepartmentShortName(String departmentShortName, Pageable pageable);


	@Query("select p.teachingCourses from Professor p")
	List<Course> getCoursesByProfessorId(Integer professorId);
}