package com.example.controlenamao.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.controlenamao.dao.CreditoDao;
import com.example.controlenamao.dao.CreditoDao;
import com.example.controlenamao.dao.GenericDao;
import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.Credito;
import com.example.controlenamao.model.Credito;

import java.util.ArrayList;
import java.util.Date;

public class CreditoController{

    private Context context;

    public CreditoController(Context context) {
        this.context = context;
    }

    public long salvarCredito(double valor, Date data){
        Credito credito = new Credito(valor, data);
        return CreditoDao.getInstancia(context).insert(credito);
    }

    public long atualizarCredito(double valor, Date data){
        Credito credito = new Credito(valor, data);
        return CreditoDao.getInstancia(context).update(credito);
    }

    public long apagarCredito(double valor, Date data){
        Credito credito = new Credito(valor, data);
        return CreditoDao.getInstancia(context).delete(credito);
    }

    public ArrayList<Credito> retornarTodosCreditos(){
        return CreditoDao.getInstancia(context).getAll();
    }

    public Credito retornarCredito(int ra){
        return CreditoDao.getInstancia(context).getById(ra);
    }

    public String validaCredito(String valor, String data){

        String mensagem = "";

        if(String.valueOf(valor) == null || String.valueOf(valor).isEmpty() || valor == "0"){
            mensagem += "O valor do credito deve ser preenchido!!\n";
            return mensagem;
        }else{
            Toast.makeText(context, String.valueOf(valor), Toast.LENGTH_SHORT).show();
        }
        if(String.valueOf(data) == null || String.valueOf(data).isEmpty() || valor == "0"){
            mensagem += "A data do credito deve ser preenchida!!\n";
            return mensagem;
        }else{
            Toast.makeText(context, String.valueOf(data), Toast.LENGTH_SHORT).show();
        }
        return mensagem;
    }
}