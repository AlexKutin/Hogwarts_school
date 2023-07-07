package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.FacultyDTO;

import java.util.List;

public interface FacultyService {

    FacultyDTO createFaculty(FacultyDTO facultyDTO);

    FacultyDTO getFacultyById(Long facultyId);

    List<FacultyDTO> getAllFaculties();

    FacultyDTO updateFaculty(FacultyDTO facultyDTO);

    FacultyDTO deleteFacultyById(Long facultyId);

    List<FacultyDTO> getAllFacultiesByColor(String color);

    FacultyDTO getFacultyByName(String facultyName);
}
