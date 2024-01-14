package ru.scarlet.authservice.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.Clock
import java.time.Instant
import java.util.*

@Entity
@Table(name = "email_two_factor_codes", indexes = [
    Index(name = "idx_emailtwofactorcode_id", columnList = "id")
])
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
class EmailTwoFactorCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    val transactionId: UUID? = UUID.randomUUID()

    @Column(unique = true)
    var code: String = ""

    @Column(unique = true)
    var email: String = ""

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    val created: Instant = Instant.now(Clock.systemUTC())


    var verified: Boolean = false

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    var verifiedAt: Instant? = null
}
