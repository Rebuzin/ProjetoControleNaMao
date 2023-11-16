package com.example.controlenamao.model;

import java.util.Date;

public class Credito {

    public double valor;
    public Date data;

    public Credito() {

    }

    public Credito(double valor, Date data) {
        this.valor = valor;
        this.data = data;
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
}
