package com.example.controlenamao;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controlenamao.Adapter.DebitoAdapter;
import com.example.controlenamao.Adapter.GastoAdapter;
import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.DebitoController;
import com.example.controlenamao.controller.GastoController;
import com.example.controlenamao.controller.HomeController;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.masks.MaskedData;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;
import java.util.Date;

public class DebitFragment extends Fragment {

    private EditText edValorDebito;
    private EditText edDataDebito;
    private Button btCadastroDebito;
    private Button btHome;

    private ListView lv;

    private DebitoController debitocontroller;
    private Spinner spinnerVeiculos;
    private Spinner spinnerGastos;

    private ArrayList<Veiculo> listaVeiculos;
    private ArrayList<Gasto> listaGastos;
    private ArrayList<Movimentacao> listaDebito;

    private HomeController hc;
    private VeiculoController vc;
    private GastoController gc;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        debitocontroller = new DebitoController(getContext());

        btCadastroDebito = getView().findViewById(R.id.btCadastroDebito);
        edValorDebito = getView().findViewById(R.id.edValorDebito);
        edDataDebito = getView().findViewById(R.id.edDataDebito);
        spinnerVeiculos = getView().findViewById(R.id.spVeiculos);
        spinnerGastos = getView().findViewById(R.id.spGastos);
        btHome = getView().findViewById(R.id.btHome);

        spinnerVeiculos = getActivity().findViewById(R.id.spVeiculosDebit);
        spinnerGastos = getActivity().findViewById(R.id.spGastos);
        vc =  new VeiculoController(getContext());
        gc =  new GastoController(getContext());
        listaVeiculos = vc.retornarTodosVeiculos();
        listaGastos = gc.retornarTodosGastos();
        VeiculoAdapter vcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        GastoAdapter gcAdapter = new GastoAdapter(this.getContext(), listaGastos);
        vcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVeiculos.setAdapter(vcAdapter);
        spinnerGastos.setAdapter(gcAdapter);

        lv = getActivity().findViewById(R.id.lvListaD);
        hc =  new HomeController(getContext());
        listaDebito = hc.retornarTodosDebitos();

        DebitoAdapter dAdapter = new DebitoAdapter(this.getContext(), listaDebito);
        dAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lv.setAdapter(dAdapter);


//      MASCÁRA PARA O CAMPO DATA
        MaskedData.addDateMask(edDataDebito);

        btCadastroDebito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDebito();
            }
        });

//      IMPLEMENTAÇÃO DE BOTÃO VOLTAR
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debit, container, false);
    }

    private void salvarDebito() {

        try {
            //Se vir em branco assume como zero
            String valorNumerico = edValorDebito.getText() != null && !edValorDebito.getText().toString().isEmpty() ? edValorDebito.getText().toString() : "0";
            String valorData = edDataDebito.getText() != null && !edDataDebito.getText().toString().isEmpty() ? edDataDebito.getText().toString() : "";
            Veiculo veiculo = null;

            //Validação para spinner veiculos(sem testar, mas não quebra)
            if (spinnerVeiculos.getSelectedItem() != null) {
                veiculo = (Veiculo) spinnerVeiculos.getSelectedItem();
            }

            Gasto gasto = null;

            //Validação para spinner veiculos(sem testar, mas não quebra)
            if (spinnerGastos.getSelectedItem() != null) {
                gasto = (Gasto) spinnerGastos.getSelectedItem();
            }

            String validacao = debitocontroller.validaDebito(
                    valorNumerico,
                    valorData,
                    veiculo,
                    gasto);

            if (!validacao.equals("")) {
                    Toast.makeText(getContext(),
                            validacao,
                            Toast.LENGTH_LONG).show();
            } else {

                Double valor = Double.parseDouble(valorNumerico);
                Date data = new Date(valorData);

                if (debitocontroller.salvarDebito(valor, data, veiculo, gasto) > 0) {
                    Toast.makeText(getContext(),
                            "Debito cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                            edValorDebito.getText().toString();
                            edDataDebito.getText();
                } else {
                    Toast.makeText(getContext(),
                            "Erro ao cadastrar Debito, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
