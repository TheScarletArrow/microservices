package ru.scarlet.authservice.entity

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
class User() {
    var email: String = ""

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(generator = "uuid")
    var id: UUID? = UUID.randomUUID()

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    var oid: Int? = null
    var username: String? = null
    var password: String? = null

    val created: LocalDateTime = LocalDateTime.now()

    var name: String = ""

    var lastName: String = ""
    var patronymic: String? = null

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")],
    )
    var roles: MutableList<Role> = mutableListOf()

}
