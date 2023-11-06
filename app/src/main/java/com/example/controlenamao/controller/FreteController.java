package com.example.controlenamao.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.controlenamao.dao.FreteDao;
import com.example.controlenamao.model.Frete;

import java.util.ArrayList;

public class FreteController{

    private Context context;

    public FreteController(Context context) {
        this.context = context;
    }

    public long salvarFrete(String name){
        Frete frete = new Frete(name);
        return FreteDao.getInstancia(context).insert(frete);
    }

    public long atualizarFrete(String name){
        Frete frete = new Frete(name);
        return FreteDao.getInstancia(context).update(frete);
    }

    public long apagarFrete(String name){
        Frete frete = new Frete(name);
        return FreteDao.getInstancia(context).delete(frete);
    }

    public ArrayList<Frete> retornarTodosFretes(){
        return FreteDao.getInstancia(context).getAll();
    }

    public Frete retornarFrete(int ra){
        return FreteDao.getInstancia(context).getById(ra);
    }

    public String validaFrete(String name){

        String mensagem = "";
        if(name == null || name.isEmpty()){
            mensagem += "O tipo do frete deve ser preenchido!!\n";
        }else{
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }
        return mensagem;
    }
}