package ru.scarlet.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Faculty", indexes = {
		@Index(name = "idx_faculty_oid", columnList = "oid")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
