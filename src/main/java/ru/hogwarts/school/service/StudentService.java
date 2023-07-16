package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long studentId);

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(StudentDTO studentDTO);

    StudentDTO deleteStudentById(Long studentId);

    List<StudentDTO> getAllStudentsByAge(int age);

    List<StudentDTO> getAllStudentsByAgeBetween(int minAge, int maxAge);

    FacultyDTO getFacultyByStudentId(Long studentId);

    List<StudentDTO> getStudentsByFacultyId(Long facultyId);

    Long getCountAllStudents();

    Float getAverageAgeStudents();

    List<StudentDTO> getTop5YoungStudents();

    List<StudentDTO> getStudentsByPage(int page, int size);
}
