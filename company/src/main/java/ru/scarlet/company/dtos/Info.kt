package ru.scarlet.company.dtos

import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties

data class Info(
        val buildProperties: BuildProperties?,
        val gitProperties: GitProperties?,
)