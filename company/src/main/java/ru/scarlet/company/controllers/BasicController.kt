package ru.scarlet.company.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.company.dtos.Info

@RestController
@RequestMapping(path=["/api/v1/", "/"])
class BasicController {
    @Autowired
    private val buildProperties: BuildProperties? = null

    @Autowired
    private val gitProperties: GitProperties? = null


    @GetMapping("/")
    fun root(): ResponseEntity<Info> {
        return ResponseEntity(Info(buildProperties, gitProperties), HttpStatus.OK)
    }
}