package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
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
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = facultyService.createFaculty(facultyDTO);
        return ResponseEntity.ok(createdFaculty);
    }

    @Operation(summary = "Получение факультета", description = "Поиск факультета по его идентификатору")
    @GetMapping("{facultyId}")
    public ResponseEntity<FacultyDTO> getFaculty(
            @PathVariable
            @Parameter(description = "Идентификатор факультета") Long facultyId) {
        FacultyDTO faculty = facultyService.getFacultyById(facultyId);
        if (faculty == FacultyDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Получение факультета по имени", description = "Поиск факультета по его имени, игнорирую регистр")
    @GetMapping()
    public ResponseEntity<FacultyDTO> getFacultyByName(
            @RequestParam
            @Parameter(description = "Имя факультета") String facultyName) {
        FacultyDTO faculty = facultyService.getFacultyByName(facultyName);
        if (faculty == FacultyDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Получение всех факультетов", description = "Получение всех факультетов в виде списка")
    @GetMapping("/all")
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
        List<FacultyDTO> faculties = facultyService.getAllFaculties();
        if (faculties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(
            summary = "Получение всех факультетов заданного цвета",
            description = "Получение списка всех факультетов заданного цвета")
    @GetMapping("/all_by_color/{color}")
    public ResponseEntity<List<FacultyDTO>> getAllFacultiesByColor(@PathVariable String color) {
        List<FacultyDTO> faculties = facultyService.getAllFacultiesByColor(color);
        if (faculties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(summary = "Редактирование факультета", description = "Изменение информации по имеющемуся факультету")
    @PutMapping
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updatedFaculty = facultyService.updateFaculty(facultyDTO);
        if (updatedFaculty == FacultyDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @Operation(summary = "Удаление факультета", description = "Удаление факультета по его идентификатору")
    @DeleteMapping("{facultyId}")
    public ResponseEntity<FacultyDTO> deleteFaculty(
            @PathVariable
            @Parameter(description = "Идентификатор студента") Long facultyId) {
        FacultyDTO faculty = facultyService.deleteFacultyById(facultyId);
        if (faculty == FacultyDTO.EMPTY) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
}
