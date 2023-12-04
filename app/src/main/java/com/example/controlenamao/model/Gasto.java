package com.example.controlenamao.model;

public class Gasto {

    private Long id;
    public String name;

    public Gasto() {
    }

    public Gasto(Long id) {
        this.id = id;
    }

    public Gasto(String name) {
        this.name = name;
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
}