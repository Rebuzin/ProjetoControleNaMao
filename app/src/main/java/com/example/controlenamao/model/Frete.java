package com.example.controlenamao.model;

public class Frete {

    private Integer id;
    public String name;

    public Frete() {
    }

    public Frete(String name) {
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