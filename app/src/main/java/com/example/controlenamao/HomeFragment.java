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
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomeFragment extends Fragment {

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        tvCombustivel = getView().findViewById(R.id.tvCombustivel);
        tvLucroFinal = getView().findViewById(R.id.tvLucroFinal);
        tvPneus = getView().findViewById(R.id.tvPneus);
        tvServicoEletrico = getView().findViewById(R.id.tvServicoEletrico);
        pieChart = getView().findViewById(R.id.piechart);

        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void setData() {
        // Set the percentage of language used
        tvCombustivel.setText(Integer.toString(40));
        tvLucroFinal.setText(Integer.toString(30));
        tvPneus.setText(Integer.toString(5));
        tvServicoEletrico.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Combustivel",
                        Integer.parseInt(tvCombustivel.getText().toString()),
                        Color.parseColor("#AF4646")));
        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        Integer.parseInt(tvLucroFinal.getText().toString()),
                        Color.parseColor("#18E622")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pneus",
                        Integer.parseInt(tvPneus.getText().toString()),
                        Color.parseColor("#403A3A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Serviço Elétrico",
                        Integer.parseInt(tvServicoEletrico.getText().toString()),
                        Color.parseColor("#FFC107")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}