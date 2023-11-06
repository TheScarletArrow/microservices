package ru.scarlet.company.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Professor", indexes = {
		@Index(name = "idx_professor_oid", columnList = "oid")
})
@Data
//препод
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;

	private String name;

	private String phone;


	private Boolean enableNotifyByPhone = true;

	private String email;

	private Boolean enableNotifyByMail = true;

	@ManyToOne
	@JoinColumn(name = "department_oid")
	private Department department;
	@ManyToOne
	@JoinColumn(name = "expertise_id")
	private Expertise expertise;

	@ManyToMany
	@JoinTable(
			name = "professor_courses",
			joinColumns = @JoinColumn(name = "professor_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> teachingCourses;

}
