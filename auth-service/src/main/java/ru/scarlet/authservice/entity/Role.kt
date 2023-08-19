package ru.scarlet.authservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import ru.scarlet.authservice.dto.RoleResponse
import java.time.Clock
import java.time.LocalDateTime

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
class Role() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(unique = true)
    var roleName: String = ""

    val isActive: Boolean = true

    val created: LocalDateTime = LocalDateTime.now(Clock.systemUTC())

    fun toRoleDto() : RoleResponse {
        return RoleResponse(name = roleName, isActive, created)
    }

    fun toMap() : HashMap<String, Any> {
        return hashMapOf("name" to this.roleName,  "isActive" to isActive, "created" to created.toString())
    }
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    val users: MutableList<User> = mutableListOf()
}