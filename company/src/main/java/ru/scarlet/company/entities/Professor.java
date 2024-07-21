package ru.scarlet.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Professor", indexes = {
        @Index(name = "idx_professor_oid", columnList = "oid"),
        @Index(name = "idx_preofessor_email", columnList = "email", unique = true)
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

    @Column(unique = true)
    private String email;

    private Boolean enableNotifyByMail = true;

    @ManyToOne
    @JoinColumn(name = "department_oid")
    private Department department;

    @JoinColumn(name = "expertise_id")
    private Long expertiseId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "professor_courses",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> teachingCourses;

}
