package com.example.controlenamao.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.controlenamao.dao.DebitoDao;
import com.example.controlenamao.model.Debito;

import java.util.ArrayList;
import java.util.Date;

public class DebitoController {

    private Context context;

    public DebitoController(Context context) {
        this.context = context;
    }

    public long salvarDebito(double valor, Date data){
        Debito debito = new Debito(valor, data);
        return DebitoDao.getInstancia(context).insert(debito);
    }

    public long atualizarDebito(double valor, Date data){
        Debito debito = new Debito(valor, data);
        return DebitoDao.getInstancia(context).update(debito);
    }

    public long apagarDebito(double valor, Date data){
        Debito debito = new Debito(valor, data);
        return DebitoDao.getInstancia(context).delete(debito);
    }

    public ArrayList<Debito> retornarTodosDebitos(){
        return DebitoDao.getInstancia(context).getAll();
    }

    public Debito retornarDebito(int ra){
        return DebitoDao.getInstancia(context).getById(ra);
    }

    public String validaDebito(String valor, String data){

        String mensagem = "";

        if(String.valueOf(valor) == null || String.valueOf(valor).isEmpty() || valor == "0"){
            mensagem += "O valor do debito deve ser preenchido!!\n";
            return mensagem;
        }else{
            Toast.makeText(context, String.valueOf(valor), Toast.LENGTH_SHORT).show();
        }
        if(String.valueOf(data) == null || String.valueOf(data).isEmpty() || valor =="0"){
            mensagem += "A data do debito deve ser preenchida!!\n";
            return mensagem;
        }else{
            Toast.makeText(context, String.valueOf(data), Toast.LENGTH_SHORT).show();
        }
        return mensagem;
    }
}