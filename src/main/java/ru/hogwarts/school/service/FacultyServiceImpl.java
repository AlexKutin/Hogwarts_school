package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty deleteFacultyById(Long facultyId) {
        Faculty deletedFaculty = facultyRepository.findById(facultyId).orElse(null);
        facultyRepository.deleteById(facultyId);
        return deletedFaculty;
    }

    @Override
    public List<Faculty> getAllFacultiesByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }
}
