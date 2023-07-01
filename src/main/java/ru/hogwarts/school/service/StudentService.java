package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();

    Student updateStudent(Student student);

    Student deleteStudentById(Long studentId);

    List<Student> getAllStudentsByAge(int age);
}
