package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
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
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }

    @Operation(summary = "Получение студента", description = "Поиск студента по его идентификатору")
    @GetMapping("{studentId}")
    public ResponseEntity<StudentDTO> getStudent(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long studentId) {
        StudentDTO student = studentService.getStudentById(studentId);
        if (student == StudentDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Получение факультета студента",
            description = "Получение факультета студента по идентификатору студента")
    @GetMapping("/faculty/{studentId}")
    public ResponseEntity<FacultyDTO> getFacultyByStudentId(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long studentId) {
        FacultyDTO facultyDTO = studentService.getFacultyByStudentId(studentId);
        if (facultyDTO == FacultyDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @Operation(summary = "Получение всех студентов", description = "Получение всех студентов в виде списка")
    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "Получение всех студентов заданного возраста",
            description = "Получение всех студентов заданного возраста в виде коллекции")
    @GetMapping("/all_by_age/{age}")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByAge(@PathVariable(name = "age") int age) {
        List<StudentDTO> students = studentService.getAllStudentsByAge(age);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "Получение всех студентов, возраст которых находится в промежутке",
            description = "Получение всех студентов возраст которых попадает в заданные границы")
    @GetMapping("/all_by_age")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        List<StudentDTO> students = studentService.getAllStudentsByAgeBetween(minAge, maxAge);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Получение студентов факультета по id факультета",
            description = "Получение списка студентов факультета по id факультета")
    @GetMapping("/all_by_faculty")
    public ResponseEntity<List<StudentDTO>> getStudentsByFacultyId(
            @RequestParam(name = "facultyId")
            @Parameter(description = "Идентификатор факультета") Long facultyId) {
        List<StudentDTO> students = studentService.getStudentsByFacultyId(facultyId);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }


    @Operation(summary = "Редактирование студента", description = "Изменение информации по имеющемуся студенту")
    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentDTO);
        if (updatedStudent == StudentDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Удаление студента", description = "Удаление студента по его идентификатору")
    @DeleteMapping("{studentId}")
    public ResponseEntity<StudentDTO> deleteStudent(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long studentId) {
        StudentDTO student = studentService.deleteStudentById(studentId);
        if (student == StudentDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
}
