package com.placement.certificate.entity;

import jakarta.persistence.*;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    public Certificate() {}

    public Certificate(Long id, int year, College college) {
        this.id = id;
        this.year = year;
        this.college = college;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public College getCollege() { return college; }
    public void setCollege(College college) { this.college = college; }
}
