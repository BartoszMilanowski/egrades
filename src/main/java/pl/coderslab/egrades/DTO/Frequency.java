package pl.coderslab.egrades.DTO;

import pl.coderslab.egrades.entity.User;

public class Frequency {

    private User student;

    private double freq;

    public Frequency(User student, double freq) {
        this.student = student;
        this.freq = freq;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }
}