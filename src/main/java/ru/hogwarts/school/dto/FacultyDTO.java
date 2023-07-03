package ru.hogwarts.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.hogwarts.school.model.Faculty;

@Schema(description = "Факультет")
public class FacultyDTO {
    public static FacultyDTO EMPTY = new FacultyDTO(0L, "", "");

    @Schema(description = "Идентификатор факультета")
    private Long id;
    @Schema(description = "Название факультета")
    private String name;
    @Schema(description = "Цвет факультета")
    private String color;

    public FacultyDTO(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static FacultyDTO fromFaculty(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    public Faculty toFaculty() {
        return new Faculty(this.getId(), this.getName(), this.getColor());
    }
}
