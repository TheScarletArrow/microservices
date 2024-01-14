package ru.scarlet.notifications.service

interface EmailSenderService {

    fun sendEmail(destination: String)

    fun sendEmail(destination: String, code: String)
}