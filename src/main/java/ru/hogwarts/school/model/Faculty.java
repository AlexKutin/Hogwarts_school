package ru.hogwarts.school.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;

@Schema
public class Faculty {
    @Schema(description = "Идентификатор факультета")
    @Min(1)
    private  Long id;
    @Schema(description = "Название факультета")
    private String name;

    @Schema(description = "Цвет факультета")
    private String color;

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


}
