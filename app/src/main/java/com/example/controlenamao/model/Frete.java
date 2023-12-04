package com.example.controlenamao.model;

public class Frete {

    private Long id;
    public String name;

    public Frete() {
    }

    public Frete(String name) {
        this.name = name;
    }

    public Frete(Long id) {
        this.id = id;
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