package ru.scarlet.company.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.util.*

@Entity
@Table(name = "student")
@Getter
@Setter
open class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    open var firstName: String? = null

    open var middleName: String? = null

    open var patronymic : String? = null

    open var username : String? = null

    @ManyToMany
//    @JoinTable(
//        name = "attendants_courses",
//        joinColumns = [JoinColumn(name = "student_id")],
//        inverseJoinColumns = [JoinColumn(name = "course_id")]
//    )
    var courseList: MutableList<Course> = ArrayList()
}