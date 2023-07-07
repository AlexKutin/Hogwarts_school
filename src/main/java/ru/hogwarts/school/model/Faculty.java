package ru.hogwarts.school.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity(name = "faculty")
public class Faculty {
    @Min(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "faculty_name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "faculty_color", nullable = false, length = 30)
    private String color;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
