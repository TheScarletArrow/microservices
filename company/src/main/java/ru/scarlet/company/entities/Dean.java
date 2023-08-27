package ru.scarlet.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
	private List<Course> teachingCourses;

}
