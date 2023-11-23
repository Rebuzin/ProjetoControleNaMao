package com.example.controlenamao.model;

public class Veiculo {

    private Integer id;
    private String renamed;

    public Veiculo() {
    }

    public Veiculo(String renamed) {
        this.renamed = renamed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRenamed() {
        return renamed;
    }

    public void setRenamed(String renamed) {
        this.renamed = renamed;
    }
}
