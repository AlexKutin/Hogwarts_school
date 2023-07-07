package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        studentDTO.setId(null);
        return StudentDTO.fromStudent(studentRepository.save(studentDTO.toStudent()));
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(StudentDTO::fromStudent)
                .orElse(StudentDTO.EMPTY);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return toStudentDTOList(studentRepository.findAll());
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        if (studentRepository.findById(studentDTO.getId()).isPresent()) {
            return StudentDTO.fromStudent(studentRepository.save(studentDTO.toStudent()));
        }
        return StudentDTO.EMPTY;
    }

    @Override
    public StudentDTO deleteStudentById(Long studentId) {
        StudentDTO deletedStudent = getStudentById(studentId);
        studentRepository.deleteById(studentId);
        return deletedStudent;
    }

    @Override
    public List<StudentDTO> getAllStudentsByAge(int age) {
        return toStudentDTOList(studentRepository.findByAge(age));
    }

    @Override
    public List<StudentDTO> getAllStudentsByAgeBetween(int minAge, int maxAge) {
        return toStudentDTOList(studentRepository.findByAgeBetween(minAge, maxAge));
    }

    @Override
    public FacultyDTO getFacultyByStudentId(Long studentId) {
        StudentDTO studentDTO = getStudentById(studentId);
        if (studentDTO == StudentDTO.EMPTY) {
            return FacultyDTO.EMPTY;
        }
        return studentDTO.getFacultyDTO();
    }

    @Override
    public List<StudentDTO> getStudentsByFacultyId(Long facultyId) {
        return  toStudentDTOList(studentRepository.findAllByFacultyId(facultyId));
    }

    private List<StudentDTO> toStudentDTOList(List<Student> students) {
        return students.stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

}
