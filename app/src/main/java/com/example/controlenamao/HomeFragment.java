package com.example.controlenamao;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.Adapter.MyAdapter;
import com.example.controlenamao.controller.CreditoController;
import com.example.controlenamao.controller.HomeController;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Gasto;
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;
    private HomeController controller;
    private String [] colors = new String[10];

    public HomeFragment() {
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


        colors[0] = "#AF4646";
        colors[1] = "#403A3A";
        colors[2] = "#FFC107";
        colors[3] = "#AF5546";
        colors[4] = "#FFC205";

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

            Double debitoCombustivel = controller.buscarDebitoByGasto(filtro, gasto);

            pieChart.addPieSlice(
                    new PieModel(
                            gasto.getName(),
                            debitoCombustivel.floatValue(),
                            Color.parseColor(colors[contador])));

            gastosTotais = gastosTotais + debitoCombustivel;

            contador = contador +1;

        }



        Double creditosFrete = controller.buscarCreditosFrete(filtro);

         Double lucro = creditosFrete - gastosTotais;

        // Set the percentage of language used
//        tvCombustivel.setText(Double.toString(debitoCombustivel));
//        tvPneus.setText(Double.toString(debitoPneus));
//        tvServicoEletrico.setText(Double.toString(debitoEletrico));
//
//        tvLucroFinal.setText(Double.toString(lucro));


        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        lucro.floatValue(),
                        Color.parseColor("#18E622")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}