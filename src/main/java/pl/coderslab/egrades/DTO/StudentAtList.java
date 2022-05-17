package pl.coderslab.egrades.DTO;

import pl.coderslab.egrades.entity.User;

public class StudentAtList {

    private User student;

    private String grades;

    private double frequency;

    public StudentAtList() {
    }

    public StudentAtList(User student, String grades) {
        this.student = student;
        this.grades = grades;
    }

    public StudentAtList(User student, String grades, double frequency) {
        this.student = student;
        this.grades = grades;
        this.frequency = frequency;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
