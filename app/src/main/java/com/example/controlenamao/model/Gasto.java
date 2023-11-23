package com.example.controlenamao.model;

public class Gasto {

    private Integer id;
    public String name;

    public Gasto() {
    }

    public Gasto(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}