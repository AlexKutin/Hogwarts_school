package ru.hogwarts.school.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity(name = "student")
@Schema(description = "Студент")
public class Student {
    @Schema(description = "Идентификатор студента")
    @Min(1)
    @Id
    @GeneratedValue
    private Long id;
    @Schema(description = "Имя студента")
    @Column(name = "student_name", nullable = false, length = 100)
    private String name;
    @Schema(description = "Возраст студента")
    @Min(7)
    @Column(name = "student_age", nullable = false)
    private int age;

    public Student() {
    }

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
