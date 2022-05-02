package pl.coderslab.egrades.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToMany
    @JoinTable(name = "present_students", joinColumns = @JoinColumn(name = "presence_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<User> presentStudents;

    @ManyToMany
    @JoinTable(name = "absent_students", joinColumns = @JoinColumn(name = "presence_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<User> absentStudents;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class group;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Set<User> getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(Set<User> presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Set<User> getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(Set<User> absentStudents) {
        this.absentStudents = absentStudents;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Class getGroup() {
        return group;
    }

    public void setGroup(Class group) {
        this.group = group;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
