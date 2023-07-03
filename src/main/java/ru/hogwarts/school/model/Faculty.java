package ru.hogwarts.school.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity(name = "faculty")
@Schema
public class Faculty {
    @Schema(description = "Идентификатор факультета")
    @Min(1)
    @Id
    @GeneratedValue
    private  Long id;
    @Schema(description = "Название факультета")
    @Column(name = "faculty_name", nullable = false, length = 50)
    private String name;

    @Schema(description = "Цвет факультета")
    @Column(name = "faculty_color", nullable = false, length = 30)
    private String color;

    public Faculty() {
    }

    public Faculty(Long id, String name, String color) {
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

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
