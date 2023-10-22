package ru.scarlet.company.entities;

import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import ru.scarlet.company.enums.CourseActive;

@Entity
@Table(name = "course", indexes = {
		@Index(name = "idx_course_oid", columnList = "oid")
})
@Getter
@Setter
//предмет
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	@Column(unique = true)
	private String courseCode;
	String courseName;

	@ManyToOne
	@JoinColumn(name = "department_oid")
	Department department;

	@ManyToMany(mappedBy = "teachingCourses")
	List<Professor> taughtByProfessors;

	@Enumerated(EnumType.ORDINAL)
	CourseActive courseActive = CourseActive.ACTIVE;
}
