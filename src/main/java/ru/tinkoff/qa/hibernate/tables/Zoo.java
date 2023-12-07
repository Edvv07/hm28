package ru.tinkoff.qa.hibernate.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zoo")
public class Zoo {
    @Id
    int id;
    @Column(name = "`name`")
    String name;

}
