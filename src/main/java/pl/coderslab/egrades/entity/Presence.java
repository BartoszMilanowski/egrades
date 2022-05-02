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
}
