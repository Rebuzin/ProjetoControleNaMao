package com.example.controlenamao.controller;

import android.content.Context;

import com.example.controlenamao.dao.MovimentacaoDao;
import com.example.controlenamao.model.Frete;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;
import java.util.Date;

public class CreditoController{

    private Context context;

    public CreditoController(Context context) {
        this.context = context;
    }

    public long salvarCredito(double valor, Date data, Veiculo veiculo, Frete frete){
        Movimentacao movimentacao = new Movimentacao(valor, data, "C", veiculo, frete);
        return MovimentacaoDao.getInstancia(context).insert(movimentacao);
    }

    public long atualizarCredito(double valor, Date data, Veiculo veiculo, Frete frete){
        Movimentacao movimentacao = new Movimentacao(valor, data, "C", veiculo, frete);
        return MovimentacaoDao.getInstancia(context).update(movimentacao);
    }

    public long apagarCredito(double valor, Date data, Veiculo veiculo, Frete frete){
        Movimentacao movimentacao = new Movimentacao(valor, data, "C", veiculo, frete);
        return MovimentacaoDao.getInstancia(context).delete(movimentacao);
    }

    public ArrayList<Movimentacao> retornarTodosCreditos(){
        return MovimentacaoDao.getInstancia(context).getAll();
    }

    public Movimentacao retornarCredito(Long ra){
        return MovimentacaoDao.getInstancia(context).getById(ra);
    }

    public String validaCredito(String valor, String data, Veiculo veiculo, Frete frete){

        String mensagem = "";

        if(veiculo == null){
            mensagem += "Deve ser cadastrado um Veículo!!\n";
            return mensagem;
        }
        if(frete == null){
            mensagem += "Deve ser cadastrado um Tipo de Frete!!\n";
            return mensagem;
        }
        if(String.valueOf(data) == null || String.valueOf(data).isEmpty() || data == "0" || data.length() < 10){
            mensagem += "A data deve ser preenchida corretamente!!\n";
            mensagem += "(dd/mm/aaaa)\n";
            return mensagem;
        }
        if(String.valueOf(valor) == null || String.valueOf(valor).isEmpty() || valor == "0"){
            mensagem += "O valor deve ser preenchido!!\n";
            return mensagem;
        }
        return mensagem;
    }
}