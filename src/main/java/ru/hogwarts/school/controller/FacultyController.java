package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@Tag(name = "Факультеты", description = "Управление факультетами")
@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Operation(summary = "Создание факультета", description = "Добавление нового факультета")
    @PostMapping
    public ResponseEntity<Faculty> createStudent(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        if (createdFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdFaculty);
    }

    @Operation(summary = "Получение факультета", description = "Поиск факультета по его идентификатору")
    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(
            @PathVariable
            @Parameter(description = "Идентификатор факультета") Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Получение всех факультетов", description = "Получение всех факультетов в виде списка")
    @GetMapping("/all")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        if (faculties == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(
            summary = "Получение всех факультетов заданного цвета",
            description = "Получение списка всех факультетов заданного цвета")
    @GetMapping("/all_by_color/{color}")
    public ResponseEntity<List<Faculty>> getAllFacultiesByColor(@PathVariable String color) {
        List<Faculty> faculties = facultyService.getAllFacultiesByColor(color);
        if (faculties == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(summary = "Редактирование факультета", description = "Изменение информации по имеющемуся факультету")
    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @Operation(summary = "Удаление факультета", description = "Удаление факультета по его идентификатору")
    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long facultyId) {
        Faculty faculty = facultyService.deleteFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
}
