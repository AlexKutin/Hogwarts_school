package ru.hogwarts.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hogwarts.school.model.Student;

@Schema(description = "Студент")
public class StudentDTO {
    public static StudentDTO EMPTY = new StudentDTO(0L, "", 0, null);
    @Schema(description = "Идентификатор студента")
    private Long id;
    @Schema(description = "Имя студента")
    private String name;
    @Schema(description = "Возраст студента")
    private int age;
    private FacultyDTO facultyDTO;

    public StudentDTO(Long id, String name, int age, FacultyDTO facultyDTO) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.facultyDTO = facultyDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }

    public static StudentDTO fromStudent(Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getAge(),
                FacultyDTO.fromFaculty(student.getFaculty()));
    }

    public Student toStudent() {
        return new Student(this.getId(), this.getName(), this.getAge(), this.getFacultyDTO().toFaculty());
    }
}
