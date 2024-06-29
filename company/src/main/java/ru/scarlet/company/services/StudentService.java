package ru.scarlet.company.services;

public interface StudentService {
    void enrollToCourse(String username, String courseCode);
    void leaveCourse(String username, String courseCode);
}
