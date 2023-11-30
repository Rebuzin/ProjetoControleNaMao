package com.example.controlenamao.controller;

import android.content.Context;

import com.example.controlenamao.dao.GastoDao;
import com.example.controlenamao.dao.MovimentacaoDao;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Gasto;

import java.util.ArrayList;

public class HomeController {

    private Context context;

    public HomeController(Context context) {
        this.context = context;
    }

    public Double buscarDebitoCombustivel(HomeFiltroVo filtro){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarDebitoCombustivel();
    }


    public Double buscarDebitoPneus(HomeFiltroVo filtro){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarDebitoPneus();
    }


    public Double buscarDebitoEletrico(HomeFiltroVo filtro){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarDebitoEletrico();
    }

    public Double buscarDebitoByGasto(HomeFiltroVo filtro, Gasto gasto){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarDebitoByGasto(gasto);
    }


    public Double buscarCreditosFrete(HomeFiltroVo filtro){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarCreditosFrete();
    }

    public ArrayList<Gasto> buscarTodosGastos(){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return GastoDao.getInstancia(context).getAll();
    }

}