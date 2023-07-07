package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        facultyDTO.setId(null);
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }

    @Override
    public FacultyDTO getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(FacultyDTO::fromFaculty)
                .orElse(FacultyDTO.EMPTY);
    }

    @Override
    public List<FacultyDTO> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        if (facultyRepository.findById(facultyDTO.getId()).isPresent()) {
            return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
        }
        return FacultyDTO.EMPTY;
    }

    @Override
    public FacultyDTO deleteFacultyById(Long facultyId) {
        FacultyDTO deletedFaculty = getFacultyById(facultyId);
        facultyRepository.deleteById(facultyId);
        return deletedFaculty;
    }

    @Override
    public List<FacultyDTO> getAllFacultiesByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color)
                .stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO getFacultyByName(String facultyName) {
        return FacultyDTO.fromFaculty(facultyRepository.findFacultyByNameIgnoreCase(facultyName));
    }
}
