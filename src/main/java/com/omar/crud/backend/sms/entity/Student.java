package com.omar.crud.backend.sms.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(
            name= "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "marks")
    private String marks;

    @Version
    @Column(name = "version")
    private Integer version;

    public Student() {

    }

    public Student(String name, String marks) {
        super();
        this.name = name;
        this.marks = marks;
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

    public String getMarks() {
        return marks;
    }
    public void setMarks(String marks) {
        this.marks = marks;
    }
}
