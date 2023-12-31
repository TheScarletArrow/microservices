package ru.scarlet.company.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Faculty", indexes = {
		@Index(name = "idx_faculty_oid", columnList = "oid")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//факультет
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;

	private String name;

	private String shortName;

	@OneToOne
	private Dean dean;

	@OneToMany(mappedBy = "faculty")
	private List<Department> departments;
}
