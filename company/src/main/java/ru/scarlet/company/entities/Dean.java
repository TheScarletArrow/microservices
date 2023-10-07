package ru.scarlet.company.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Dean", indexes = {
		@Index(name = "idx_dean_oid", columnList = "oid")
})
@Getter
@Setter
//декан
public class Dean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;

	private String firstName;

	private String lastName;

	private String patronymic;

	@ManyToMany
	@JoinTable(
			name = "dean_courses",
			joinColumns = @JoinColumn(name = "dean_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	@JsonIgnore
	private List<Course> teachingCourses;

	@OneToOne
	Faculty faculty;
}
