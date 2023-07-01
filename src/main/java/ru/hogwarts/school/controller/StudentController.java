package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Tag(name = "Студенты", description = "Управление студентами")
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Создание студента", description = "Добавление нового студента")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        if (createdStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdStudent);
    }

    @Operation(summary = "Получение студента", description = "Поиск студента по его идентификатору")
    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Получение всех студентов", description = "Получение всех студентов в виде списка")
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "Получение всех студентов заданного возраста",
            description = "Получение всех студентов заданного возраста в виде коллекции")
    @GetMapping("/all_by_age/{age}")
    public ResponseEntity<List<Student>> getAllStudentsByAge(@PathVariable int age) {
        List<Student> students = studentService.getAllStudentsByAge(age);
        if (students == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Редактирование студента", description = "Изменение информации по имеющемуся студенту")
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Удаление студента", description = "Удаление студента по его идентификатору")
    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long studentId) {
        Student student = studentService.deleteStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
}
