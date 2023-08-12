package ru.scarlet.authservice.entity

import jakarta.persistence.*
import lombok.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
class User (
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(generator = "uuid")
     val id: UUID? = null,
    val username: String? = null,
    var password: String? = null

)
