package com.example.controlenamao.model;

import java.util.Date;

public class Debito {

    public double valor;
    public Date data;

    public Debito() {

    }

    public Debito(double valor, Date data) {
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