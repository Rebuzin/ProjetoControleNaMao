package com.example.controlenamao.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.controlenamao.Adapter.VeiculoAdapter;
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

    public long apagarVeiculo(VeiculoAdapter id){
        Veiculo veiculo = new Veiculo(String.valueOf(id));
        return VeiculoDao.getInstancia(context).delete(veiculo);
    }

    public ArrayList<Veiculo> retornarTodosVeiculos(){
        return VeiculoDao.getInstancia(context).getAll();
    }

    public Veiculo retornarVeiculo(Long id){
        return VeiculoDao.getInstancia(context).getById(id);
    }

    public String validaVeiculo(String renamed){

        String mensagem = "";
        if(renamed == null || renamed.isEmpty()){
            mensagem += "A placa do veiculo deve ser preenchida!!\n";
        }else if (renamed.length() < 7){
            mensagem += "Placa incorreta!!\n";
            mensagem += "A placa deve conter no mínimo 7 caracteres!!\n";
        }else{
            Toast.makeText(context, renamed, Toast.LENGTH_SHORT).show();
        }
        return mensagem;
    }
}