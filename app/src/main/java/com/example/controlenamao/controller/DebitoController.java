package com.example.controlenamao.controller;

import android.content.Context;

import com.example.controlenamao.dao.MovimentacaoDao;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;
import java.util.Date;

public class DebitoController {

    private Context context;

    public DebitoController(Context context) {
        this.context = context;
    }

    public long salvarDebito(double valor, Date data, Veiculo veiculo, Gasto gasto){
        Movimentacao debito = new Movimentacao(valor, data, "D",  veiculo, gasto);
        return MovimentacaoDao.getInstancia(context).insert(debito);
    }

    public long atualizarDebito(double valor, Date data, Veiculo veiculo, Gasto gasto){
        Movimentacao debito = new Movimentacao(valor, data, "D",  veiculo, gasto);
        return MovimentacaoDao.getInstancia(context).update(debito);
    }

    public long apagarDebito(double valor, Date data, Veiculo veiculo, Gasto gasto){
        Movimentacao debito = new Movimentacao(valor, data, "D",  veiculo, gasto);
        return MovimentacaoDao.getInstancia(context).delete(debito);
    }

    public ArrayList<Movimentacao> retornarTodosDebitos(){
        return MovimentacaoDao.getInstancia(context).getAll();
    }

    public Movimentacao retornarDebito(int ra){
        return MovimentacaoDao.getInstancia(context).getById(ra);
    }

    public String validaDebito(String valor, String data, Veiculo veiculo, Gasto gasto){

        String mensagem = "";


        if(veiculo == null){
            mensagem += "Deve ser informado um Veiculo!!\n";
            return mensagem;
        }
        if(gasto == null){
            mensagem += "Deve ser informado um Gasto!\n";
            return mensagem;
        }

        if(String.valueOf(data) == null || String.valueOf(data).isEmpty() || data == "0"){
            mensagem += "A data deve ser preenchida!!\n";
            return mensagem;
        }
        if(String.valueOf(valor) == null || String.valueOf(valor).isEmpty() || valor == "0"){
            mensagem += "O valor deve ser preenchido!\n";
            return mensagem;
        }
        return mensagem;
    }
}