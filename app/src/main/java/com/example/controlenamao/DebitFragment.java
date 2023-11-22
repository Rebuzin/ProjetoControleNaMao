package com.example.controlenamao;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controlenamao.Adapter.GastoAdapter;
import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.DebitoController;
import com.example.controlenamao.controller.GastoController;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.masks.MaskedData;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;
import java.util.Date;

public class DebitFragment extends Fragment {

    private EditText edValorDebito;
    private EditText edDataDebito;
    private Button btCadastroDebito;
    private DebitoController debitocontroller;
    private Spinner spinnerVeiculos;
    private Spinner spinnerGastos;

    private ArrayList<Veiculo> listaVeiculos;
    private ArrayList<Gasto> listaGastos;

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

//      Máscara para o campo data
        MaskedData.addDateMask(edDataDebito);

        btCadastroDebito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDebito();
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

            String validacao = debitocontroller.validaDebito(
                    valorNumerico,
                    valorData);

            if (!validacao.equals("")) {
                if (validacao.contains("data")) {

                    Toast.makeText(getContext(),
                            validacao,
                            Toast.LENGTH_LONG).show();
                    
                } else if (validacao.contains("valor")) {

                    Toast.makeText(getContext(),
                            validacao,
                            Toast.LENGTH_LONG).show();
                }
            } else {

                Double valor = Double.parseDouble(valorNumerico);
                Date data = new Date(valorData);

                if (debitocontroller.salvarDebito(valor, data) > 0) {
                    Toast.makeText(getContext(),
                            "Debito cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
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
