package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Student;
import ru.scarlet.company.repository.CourseRepository;
import ru.scarlet.company.repository.StudentRepository;
import ru.scarlet.company.services.StudentService;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public void enrollToCourse(String username, String courseCode) {
        Student student = studentRepository.findByUsername(username);
        Course course = courseRepository.findByCourseCode(courseCode);
        student.getCourseList().add(course);
    }

    @Override
    @Transactional
    public void leaveCourse(String username, String courseCode) {
        Student student = studentRepository.findByUsername(username);
        Course course = courseRepository.findByCourseCode(courseCode);
        student.getCourseList().removeIf(it->it.equals(course));
    }
}
