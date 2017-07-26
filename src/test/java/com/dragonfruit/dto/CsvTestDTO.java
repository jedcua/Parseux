package com.dragonfruit.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "age"})
public class CsvTestDTO {
    private String name;
    private Integer age;

    public CsvTestDTO() {}

    public CsvTestDTO(final String name, final Integer age) {
        this();
        this.name = name;
        this.age = age;
    }

    public final String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
