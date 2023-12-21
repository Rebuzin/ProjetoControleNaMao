package com.example.controlenamao;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.Adapter.HomeAdapter;
import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.CreditoController;
import com.example.controlenamao.controller.DebitoController;
import com.example.controlenamao.controller.HomeController;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.masks.MaskedData;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;
import com.example.controlenamao.model.vo.HomeVo;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class FaturamentoFragment extends Fragment {

    // Create the object of TextView and PieChart class
    TextView tvCombustivel, tvLucroFinal, tvPneus, tvServicoEletrico;
    PieChart pieChart;
    private HomeController controller;
    private String [] colors = new String[10];

    private ListView lv;

    private ImageButton btFiltro;
    TextView infoTv;

    private EditText edDataIn;
    private EditText edDataFn;
    private ImageButton btBuscar;
    private Spinner spinnerVeiculos;

    private ArrayList<Veiculo> listaVeiculos;

    private VeiculoController vc;

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
        btFiltro = getView().findViewById(R.id.btFiltro);
        btFiltro = getActivity().findViewById(R.id.btFiltro);

        btFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

//        tvCombustivel = getView().findViewById(R.id.tvCombustivel);
//        tvPneus = getView().findViewById(R.id.tvPneus);
//        tvServicoEletrico = getView().findViewById(R.id.tvServicoEletrico);
//        tvLucroFinal = getView().findViewById(R.id.tvLucroFinal);
        pieChart = getView().findViewById(R.id.piechart);

        lv = getActivity().findViewById(R.id.lvHome);


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

    //FUNÇÃO PARA MOSTRAR O DIALOG CUSTOMIZADO DE BUSCA
    void showCustomDialog() {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText edDataIn = dialog.findViewById(R.id.edDataIn);
        final EditText edDataFn = dialog.findViewById(R.id.edDataFn);
        final Spinner spinnerVeiculos = dialog.findViewById(R.id.spVeiculos);
        Button btBuscar = dialog.findViewById(R.id.btBuscar);

//        spinnerVeiculos = getActivity().findViewById(R.id.spVeiculos);
        vc =  new VeiculoController(getContext());
        listaVeiculos = vc.retornarTodosVeiculos();
        VeiculoAdapter vcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        vcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVeiculos.setAdapter(vcAdapter);

//        MÁSCARA PARA O CAMPO DATA
        MaskedData.addDateMask(edDataIn);
        MaskedData.addDateMask(edDataFn);

//        final Spinner nameEt = dialog.findViewById(R.id.spVeiculos);
//        final EditText dataIn = dialog.findViewById(R.id.edDataIn);
//        final EditText dataFn = dialog.findViewById(R.id.edDataFn);
//        Button btBuscar = dialog.findViewById(R.id.btBuscar);
//
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(spinnerVeiculos.getSelectedItem());
                String dataInicial = edDataIn.getText().toString();
                String dataFinal = edDataFn.getText().toString();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    private void setData() {

        List<HomeVo> listHome = new ArrayList<>();

        ArrayList<Gasto> listaGasto = controller.buscarTodosGastos();

        HomeFiltroVo filtro = new HomeFiltroVo();

        Double gastosTotais = 0d;

        int contador = 0;
        for(Gasto gasto : listaGasto){

            Double valorDebito = controller.buscarDebitoByGasto(filtro, gasto);

//            if(contador == 0){
//                tvCombustivel.setText("R$" + Double.toString(valorDebito));
//            }
//            if(contador == 1) {
//                tvPneus.setText("R$" + Double.toString(valorDebito));
//            }
//            if(contador == 2) {
//                tvServicoEletrico.setText("R$" + Double.toString(valorDebito));
//            }

            pieChart.addPieSlice(
                    new PieModel(
                            gasto.getName(),
                            valorDebito.floatValue(),
                            Color.parseColor(colors[contador])));

            gastosTotais = gastosTotais + valorDebito;

            HomeVo homeVo = new HomeVo();
            homeVo.setTipo(gasto.getName());
            homeVo.setValor(valorDebito.floatValue());
            listHome.add(homeVo);

            contador = contador +1;

        }

        Double creditosFrete = controller.buscarCreditosFrete(filtro);

        Double lucro = creditosFrete - gastosTotais;

        HomeVo homeVo = new HomeVo();
        homeVo.setTipo("Lucro Final");
        homeVo.setValor(lucro);
        listHome.add(homeVo);


         //Setando list
        HomeAdapter gcAdapter = new HomeAdapter(this.getContext(), listHome);
        gcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_2);
        lv.setAdapter(gcAdapter);


//        tvLucroFinal.setText("R$" + Double.toString(lucro));


        pieChart.addPieSlice(
                new PieModel(
                        "Lucro Final",
                        lucro.floatValue(),
                        Color.parseColor("#18E622")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}