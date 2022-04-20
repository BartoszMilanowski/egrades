package pl.coderslab.egrades.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NotNull
    @Min(1)
    @Max(6)
    private double gradeValue;

    @NotBlank(message = "Opis nie może pozostać pusty")
    @Size(min = 5, max = 250, message = "Opis powinien zawierać od 5 do 250 znaków")
    private String gradeDescription;

    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", gradeValue=" + gradeValue +
                ", gradeDescription='" + gradeDescription + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}
