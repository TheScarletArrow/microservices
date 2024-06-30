package ru.scarlet.company.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scarlet.company.entities.Course;
import ru.scarlet.company.entities.Student;
import ru.scarlet.company.repository.StudentRepository;
import ru.scarlet.company.services.CourseService;
import ru.scarlet.company.services.StudentService;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Override
    @Transactional
    public void enrollToCourse(String username, Integer courseId) {
        Student student = studentRepository.findByUsername(username);
        Course course = courseService.getCourseByIdE(courseId);
        student.getCourseList().add(course);
    }

    @Override
    @Transactional
    public void leaveCourse(String username, Integer courseId) {
        Student student = studentRepository.findByUsername(username);
        Course course = courseService.getCourseByIdE(courseId);
        student.getCourseList().remove((course));
        course.getAttendants().remove(student);
    }
}
