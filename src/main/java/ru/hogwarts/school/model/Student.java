package ru.hogwarts.school.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;

@Schema(description = "Студент")
public class Student {
    @Schema(description = "Идентификатор студента")
    @Min(1)
    private Long id;
    @Schema(description = "Имя студента")
    private String name;
    @Schema(description = "Возраст студента")
    @Min(7)
    private int age;

    public Student(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
