package ru.scarlet.company.services;

public interface StudentService {
    void enrollToCourse(String username, Integer courseId);
    void leaveCourse(String username, Integer courseId);
}
