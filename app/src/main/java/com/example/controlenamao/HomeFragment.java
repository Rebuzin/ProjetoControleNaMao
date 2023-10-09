package com.example.controlenamao;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomeFragment extends Fragment{

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Link those objects with their respective
        // id's that we have given in .XML file
//        tvCombustivel = tvCombustivel.findViewById(R.id.tvCombustivel);
//        tvLucroFinal = tvLucroFinal.findViewById(R.id.tvLucroFinal);
//        tvPneus = tvPneus.findViewById(R.id.tvPneus);
//        tvServicoEletrico = tvServicoEletrico.findViewById(R.id.tvServicoEletrico);
//        pieChart = pieChart.findViewById(R.id.piechart);
//
//        setData();

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        // Link those objects with their respective
//        // id's that we have given in .XML file
//        tvCombustivel = tvCombustivel.findViewById(R.id.tvCombustivel);
//        tvLucroFinal = tvLucroFinal.findViewById(R.id.tvLucroFinal);
//        tvPneus = tvPneus.findViewById(R.id.tvPneus);
//        tvServicoEletrico = tvServicoEletrico.findViewById(R.id.tvServicoEletrico);
//        pieChart = pieChart.findViewById(R.id.piechart);
//
//        setData();
//    }

    private void setData(){
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
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        Integer.parseInt(tvLucroFinal.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pneus",
                        Integer.parseInt(tvPneus.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Serviço Elétrico",
                        Integer.parseInt(tvServicoEletrico.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

}