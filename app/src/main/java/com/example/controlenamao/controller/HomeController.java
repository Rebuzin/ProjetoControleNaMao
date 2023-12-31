package com.example.controlenamao.controller;

import android.content.Context;

import com.example.controlenamao.dao.GastoDao;
import com.example.controlenamao.dao.MovimentacaoDao;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Frete;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;

import java.util.ArrayList;

public class HomeController {

    private Context context;

    public HomeController(Context context) {
        this.context = context;
    }

    public Double buscarDebitoByGasto(HomeFiltroVo filtro, Gasto gasto){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarDebitoByGasto(gasto, filtro);
    }


    public Double buscarCreditosFrete(HomeFiltroVo filtro){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return MovimentacaoDao.getInstancia(context).buscarCreditosFrete(filtro);
    }

    public ArrayList<Gasto> buscarTodosGastos(){
        //Aqui podeira converter os filtros para mandar pro DAO, por exemplo, periodo de datas, etc
        return GastoDao.getInstancia(context).getAll();
    }

    public ArrayList<Movimentacao> retornarTodosCreditos(){
        return MovimentacaoDao.getInstancia(context).getAllCredito();
    }

    public Long apagarCredito(Movimentacao movimentacao){
        return MovimentacaoDao.getInstancia(context).delete(movimentacao);
    }
    public Long apagarDebito(Movimentacao movimentacao){
        return MovimentacaoDao.getInstancia(context).delete(movimentacao);
    }

    public ArrayList<Movimentacao> retornarTodosDebitos(){
        return MovimentacaoDao.getInstancia(context).getAllDebito();
    }


}