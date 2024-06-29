package ru.scarlet.company.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.company.client.AuthClient
import ru.scarlet.company.dtos.Info
import ru.scarlet.company.dtos.UsernameFromToken
import ru.scarlet.company.excpetions.BadRequest.BadRequestExceprion

@RestController
@RequestMapping(path=["/api/v1/", "/"])
open class BasicController {
    @Autowired
    private val buildProperties: BuildProperties? = null

    @Autowired
    private val gitProperties: GitProperties? = null

    @Autowired
    private val authClient: AuthClient? = null


    @GetMapping("/")
    fun root(): ResponseEntity<Info> {
        return ResponseEntity(Info(buildProperties, gitProperties), HttpStatus.OK)
    }

    protected fun getUsernameFromToken(token: String): String {
        val response: ResponseEntity<UsernameFromToken> = authClient!!.getUsername(token)
        if (response.statusCode.is2xxSuccessful) {
            return response.body.username
        } else throw BadRequestExceprion()
    }
}