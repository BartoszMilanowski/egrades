package pl.coderslab.egrades.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String className;

    @NotNull
    @ManyToOne
    private User supervisingTeacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public User getSupervisingTeacher() {
        return supervisingTeacher;
    }

    public void setSupervisingTeacher(User supervisingTeacher) {
        this.supervisingTeacher = supervisingTeacher;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", supervisingTeacher=" + supervisingTeacher +
                '}';
    }
}
