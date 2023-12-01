package com.example.controlenamao;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.controller.CreditoController;
import com.example.controlenamao.controller.DebitoController;
import com.example.controlenamao.controller.HomeController;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class FaturamentoFragment extends Fragment {

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;
    private HomeController controller;
    private String [] colors = new String[10];

//    private ListView lvFaturamento;
//    private CreditoController creditocontroller;
//    private DebitoController debitocontroller;
//
//    private ArrayList<Movimentacao> listaMovimentacao;

    public FaturamentoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        controller = new HomeController(getContext());

        tvCombustivel = getView().findViewById(R.id.tvCombustivel);
        tvPneus = getView().findViewById(R.id.tvPneus);
        tvServicoEletrico = getView().findViewById(R.id.tvServicoEletrico);
        tvLucroFinal = getView().findViewById(R.id.tvLucroFinal);
        pieChart = getView().findViewById(R.id.piechart);

//        lvFaturamento = getView().findViewById(R.id.lvFaturamento);
//        lvFaturamento = getActivity().findViewById(R.id.lvFaturamento);

//        creditocontroller =  new CreditoController(getContext());
//        debitocontroller =  new DebitoController(getContext());

//        listaMovimentacao = creditocontroller.retornarTodosCreditos();
//        listaMovimentacao = debitocontroller.retornarTodosDebitos();

//        MovimentacaoAdapter mAdapter = new MovimentacaoAdapter(this.getContext(), listaMovimentacao);
//        mAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

//        ArrayList<Gasto> listaMovimentacao = controller.buscarTodosGastos();

//        for(Gasto gasto : listaMovimentacao){
//
//            HomeFiltroVo filtro = new HomeFiltroVo();
//
//            Double gastosTotais = 0d;
//
//            Double debitoCombustivel = controller.buscarDebitoByGasto(filtro, gasto);
//
//            gastosTotais = gastosTotais + debitoCombustivel;
//
//            Double creditosFrete = controller.buscarCreditosFrete(filtro);
//
//            Double lucro = creditosFrete - gastosTotais;
//
//        }

//        Double creditosFrete = controller.buscarCreditosFrete(filtro);
//
//        Double lucro = creditosFrete - gastosTotais;
//        lvFaturamento.setAdapter(mAdapter);


        colors[0] = "#AF4646";
        colors[1] = "#403A3A";
        colors[2] = "#FFC107";
        colors[3] = "#00008B";
        colors[4] = "#2F4F4F";

        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    private void setData() {

        ArrayList<Gasto> listaGasto = controller.buscarTodosGastos();

        HomeFiltroVo filtro = new HomeFiltroVo();

        Double gastosTotais = 0d;

        int contador = 0;
        for(Gasto gasto : listaGasto){

            Double valorDebito = controller.buscarDebitoByGasto(filtro, gasto);

            if(contador == 0){
                tvCombustivel.setText(Double.toString(valorDebito));
            }
            if(contador == 1) {
                tvPneus.setText(Double.toString(valorDebito));
            }
            if(contador == 2) {
                tvServicoEletrico.setText(Double.toString(valorDebito));
            }

            pieChart.addPieSlice(
                    new PieModel(
                            gasto.getName(),
                            valorDebito.floatValue(),
                            Color.parseColor(colors[contador])));

            gastosTotais = gastosTotais + valorDebito;

            contador = contador +1;

        }

        Double creditosFrete = controller.buscarCreditosFrete(filtro);

         Double lucro = creditosFrete - gastosTotais;


        tvLucroFinal.setText(Double.toString(lucro));


        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        lucro.floatValue(),
                        Color.parseColor("#18E622")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}