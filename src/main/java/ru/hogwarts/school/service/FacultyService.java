package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(Long facultyId);

    List<Faculty> getAllFaculties();

    Faculty updateFaculty(Faculty faculty);

    Faculty deleteFacultyById(Long facultyId);

    List<Faculty> getAllFacultiesByColor(String color);
}
