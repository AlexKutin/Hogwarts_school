package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long generatedFacultyId = 1L;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId);
        faculties.put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }

    @Override
    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return new ArrayList<>(faculties.values());
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        Long id = faculty.getId();
        if (faculties.containsKey(id)) {
            faculties.put(id, faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty deleteFacultyById(Long facultyId) {
        return faculties.remove(facultyId);
    }

    @Override
    public List<Faculty> getAllFacultiesByColor(String color) {
        return faculties.values()
                .stream()
                .filter(faculty -> faculty.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
