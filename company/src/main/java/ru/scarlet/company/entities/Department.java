package ru.scarlet.company.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Department", indexes = {
		@Index(name = "idx_department_oid", columnList = "oid")
})
@Data
//кафедра
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;

	@Column(unique = true)
	private String shortName;

	@Column(unique = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	@ManyToMany
	@JoinTable(
			name = "department_course",
			joinColumns = @JoinColumn(name = "department_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> teachingCourses = new ArrayList<>();

//	@ManyToMany
//			@JoinTable(name = "department_professors",
//			joinColumns = @JoinColumn(name = "department_id"),
//			inverseJoinColumns = @JoinColumn(name = "professor_id"))
//	List<Professor> professors;
}
