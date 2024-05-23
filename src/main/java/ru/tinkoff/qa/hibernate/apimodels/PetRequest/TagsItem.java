package ru.tinkoff.qa.hibernate.apimodels.PetRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagsItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}