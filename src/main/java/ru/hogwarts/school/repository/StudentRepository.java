package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    List<Student> findByAge(Integer age);

    List<Student> findByAgeBetween(Integer min, Integer max);

    List<Student> findAllByFacultyId(Long facultyId);

    @Query(value = "SELECT COUNT(*) FROM student s", nativeQuery = true)
    long findCountAllStudents();

    @Query(value = "SELECT round(AVG(s.student_age), 2) from student s", nativeQuery = true)
    float findAverageAgeStudents();

    @Query(value = "SELECT * from student s order by s.student_age limit 5", nativeQuery = true)
    List<Student> findTop5YoungStudents();



}
