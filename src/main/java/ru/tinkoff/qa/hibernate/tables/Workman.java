package ru.tinkoff.qa.hibernate.tables;
import jakarta.persistence.*;

@Entity

@Table(name = "workman")
public class Workman {
    @Id
    int id;
    @Column(name =  "`name`", nullable = false)
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "`position`")
    int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
