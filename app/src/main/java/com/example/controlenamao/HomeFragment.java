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
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomeFragment extends Fragment {

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;
    private HomeController controller;

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

        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void setData() {

        HomeFiltroVo filtro = new HomeFiltroVo();

        Double debitoCombustivel = controller.buscarDebitoCombustivel(filtro);
        Double debitoPneus = controller.buscarDebitoPneus(filtro);
        Double debitoEletrico = controller.buscarDebitoEletrico(filtro);
        Double creditosFrete = controller.buscarCreditosFrete(filtro);

        Double lucro = creditosFrete - (debitoCombustivel + debitoPneus + debitoEletrico);

        // Set the percentage of language used
        tvCombustivel.setText(Double.toString(debitoCombustivel));
        tvPneus.setText(Double.toString(debitoPneus));
        tvServicoEletrico.setText(Double.toString(debitoEletrico));

        tvLucroFinal.setText(Double.toString(lucro));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Combustivel",
                        Float.parseFloat(tvCombustivel.getText().toString()),
                        Color.parseColor("#AF4646")));

        pieChart.addPieSlice(
                new PieModel(
                        "Pneus",
                        Float.parseFloat(tvPneus.getText().toString()),
                        Color.parseColor("#403A3A")));

        pieChart.addPieSlice(
                new PieModel(
                        "Serviço Elétrico",
                        Float.parseFloat(tvServicoEletrico.getText().toString()),
                        Color.parseColor("#FFC107")));



        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        Float.parseFloat(tvLucroFinal.getText().toString()),
                        Color.parseColor("#18E622")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}