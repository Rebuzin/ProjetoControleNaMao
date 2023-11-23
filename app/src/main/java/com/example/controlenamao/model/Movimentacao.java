package com.example.controlenamao.model;

import java.util.Date;

public class Movimentacao {

    private Integer id;
    public double valor;
    public Date data;
    public String tipo;
    public Veiculo veiculo;
    public Frete frete;
    public Gasto gasto;

    public Movimentacao() {

    }

    public Movimentacao(double valor, Date data, String tipo) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }
    public Movimentacao(double valor, Date data, String tipo, Veiculo veiculo, Frete frete) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.frete = frete;
    }
    public Movimentacao(double valor, Date data, String tipo, Veiculo veiculo, Gasto gasto) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.gasto = gasto;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Frete getFrete() {
        return frete;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
}
