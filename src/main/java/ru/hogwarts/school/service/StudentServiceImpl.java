package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long generatedStudentId = 1L;

    @Override
    public Student createStudent(Student student) {
        student.setId(generatedStudentId);
        students.put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student updateStudent(Student student) {
        Long id = student.getId();
        if (students.containsKey(id)) {
            students.put(id, student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudentById(Long studentId) {
        return students.remove(studentId);
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        return students.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

}
