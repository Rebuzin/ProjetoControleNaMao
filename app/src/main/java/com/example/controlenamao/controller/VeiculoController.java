package com.example.controlenamao.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.controlenamao.dao.VeiculoDao;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;

public class VeiculoController {

    private Context context;

    public VeiculoController(Context context) {
        this.context = context;
    }

    public long salvarVeiculo(String renamed){
        Veiculo veiculo = new Veiculo(renamed);
        return VeiculoDao.getInstancia(context).insert(veiculo);
    }

    public long atualizarVeiculo(String renamed){
        Veiculo veiculo = new Veiculo(renamed);
        return VeiculoDao.getInstancia(context).update(veiculo);
    }

    public long apagarVeiculo(String renamed){
        Veiculo veiculo = new Veiculo(renamed);
        return VeiculoDao.getInstancia(context).delete(veiculo);
    }

    public ArrayList<Veiculo> retornarTodosVeiculos(){
        return VeiculoDao.getInstancia(context).getAll();
    }

    public Veiculo retornarVeiculo(int ra){
        return VeiculoDao.getInstancia(context).getById(ra);
    }

    public String validaVeiculo(String renamed){
        Toast.makeText(context, renamed, Toast.LENGTH_SHORT).show();
        String mensagem = "";
        if(renamed == null || renamed.isEmpty()){
            mensagem += "A placa do veiculo deve ser preenchida!!\n";
        }
        return mensagem;
    }

}