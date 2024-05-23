package ru.tinkoff.qa.hibernate.apimodels.PetResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetGetResponseNotFound {

    @JsonProperty("code")
    private int code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    public void setCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}