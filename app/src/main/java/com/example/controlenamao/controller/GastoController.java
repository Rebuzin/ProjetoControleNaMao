package com.example.controlenamao.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.controlenamao.dao.GastoDao;
import com.example.controlenamao.model.Gasto;

import java.util.ArrayList;

public class GastoController {

    private Context context;

    public GastoController(Context context) {
        this.context = context;
    }

    public long salvarGasto(String name){
        Gasto gasto = new Gasto(name);
        return GastoDao.getInstancia(context).insert(gasto);
    }

    public long atualizarGasto(String name){
        Gasto gasto = new Gasto(name);
        return GastoDao.getInstancia(context).update(gasto);
    }

    public long apagarGasto(Long id){
        Gasto gasto = new Gasto(id);
        return GastoDao.getInstancia(context).delete(gasto);
    }

    public ArrayList<Gasto> retornarTodosGastos(){
        return GastoDao.getInstancia(context).getAll();
    }

    public Gasto retornarGasto(Long ra){
        return GastoDao.getInstancia(context).getById(ra);
    }

    public String validaGasto(String name){

        String mensagem = "";
        if(name == null || name.isEmpty()){
            mensagem += "O tipo do gasto deve ser preenchido!!\n";
        }else if (name.length() < 3){
            mensagem += "O tipo do gasto deve conter no mínimo 3 letras!!\n";
        }else{
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }
        return mensagem;
    }
}