package ru.scarlet.company.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.scarlet.company.enums.CourseActive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course", indexes = {
		@Index(name = "idx_course_oid", columnList = "oid")
})
@Getter
@Setter
@Accessors(chain = true)
//предмет
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	@Column(unique = true)
	private String courseCode;
	String courseName;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "department_oid")
	Department department;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "teachingCourses")
	List<Professor> taughtByProfessors;

	@Enumerated(EnumType.ORDINAL)
	CourseActive courseActive = CourseActive.ACTIVE;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	List<FileData> files = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	List<Student> attendants = new ArrayList<>();
}
