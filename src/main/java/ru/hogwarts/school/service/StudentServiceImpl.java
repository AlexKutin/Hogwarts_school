package ru.hogwarts.school.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final FacultyService facultyService;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository, FacultyService facultyService) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.facultyService = facultyService;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        studentDTO.setId(null);
        Student student = studentDTO.toStudent();
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException("facultyId not found: " + studentDTO.getFacultyId()));
        student.setFaculty(faculty);
        return StudentDTO.fromStudent(studentRepository.save(student));
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
        return  facultyService.getFacultyById(studentDTO.getFacultyId());
    }

    @Override
    public List<StudentDTO> getStudentsByFacultyId(Long facultyId) {
        return  toStudentDTOList(studentRepository.findAllByFacultyId(facultyId));
    }

    @Override
    public Long getCountAllStudents() {
        return studentRepository.findCountAllStudents();
    }

    @Override
    public Float getAverageAgeStudents() {
        return studentRepository.findAverageAgeStudents();
    }

    @Override
    public List<StudentDTO> getTop5YoungStudents() {
        return toStudentDTOList(studentRepository.findTop5YoungStudents());
    }

    @Override
    public List<StudentDTO> getStudentsByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Student> students = studentRepository.findAll(pageRequest).getContent();
        return toStudentDTOList(students);
    }

    private List<StudentDTO> toStudentDTOList(List<Student> students) {
        return students.stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    private List<StudentDTO> toStudentDTOList(Iterable<Student> students) {
        return StreamSupport.stream(students.spliterator(), false)
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }
}
