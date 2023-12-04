package com.example.controlenamao.model;

public class Veiculo {

    private Long id;
    private String renamed;

    public Veiculo() {
    }

    public Veiculo(String renamed) {
        this.renamed = renamed;
    }

    public Veiculo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRenamed() {
        return renamed;
    }

    public void setRenamed(String renamed) {
        this.renamed = renamed;
    }
}
