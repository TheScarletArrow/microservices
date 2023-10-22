package ru.scarlet.company.enums

enum class CourseActive(val key: Int, val value: String) {
    ACTIVE(0, "ACTIVE"),
    DEACTIVATED(1, "DEACTIVATED"),
    CLOSED(2, "CLOSED"),
}