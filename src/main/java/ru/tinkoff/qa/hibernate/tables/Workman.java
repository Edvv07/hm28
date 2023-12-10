package ru.tinkoff.qa.hibernate.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "workman")
public class Workman {
    @Id
    private int id;
    @Column(name = "\"name\"", nullable = true)
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "\"position\"")
    private int position;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }
}
