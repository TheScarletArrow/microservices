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
    var username: String? = null
    var password: String? = null

    val created: LocalDateTime = LocalDateTime.now()

    var name: String = ""

    var lastName: String = ""
    var patronymic: String? = null

}
