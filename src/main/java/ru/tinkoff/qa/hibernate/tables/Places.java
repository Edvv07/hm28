package ru.tinkoff.qa.hibernate.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "places")
public class Places {

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(final int row) {
        this.row = row;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(final int placeNum) {
        this.placeNum = placeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "\"row\"")
    private int row;

    @Column(name = "place_num")
    private int placeNum;
    @Column(name = "\"name\"")
    private String name;
}





