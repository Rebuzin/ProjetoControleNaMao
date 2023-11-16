package com.example.controlenamao;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlenamao.controller.CreditoController;

import java.util.Date;

public class CreditFragment extends Fragment {

    private EditText edValorCredito;
    private EditText edDataCredito;
    private Button btCadastroCredito;
    private CreditoController creditocontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        creditocontroller = new CreditoController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroCredito = getView().findViewById(R.id.btCadastroCredito);
        edValorCredito = getView().findViewById(R.id.edValorCredito);
        edDataCredito = getView().findViewById(R.id.edDataCredito);

        btCadastroCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCredito();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit, container, false);

    }

    private void salvarCredito() {

        //Se vir em branco assume como zero
        String valorNumerico = edValorCredito.getText() != null && !edValorCredito.getText().toString().isEmpty() ? edValorCredito.getText().toString() : "0";
        String valorData = edDataCredito.getText() != null && !edDataCredito.getText().toString().isEmpty() ? edDataCredito.getText().toString() : "";

        String validacao = creditocontroller.validaCredito(
                valorNumerico,
                valorData);

        if (!validacao.equals("")) {
            if (validacao.contains("data")) {
                edDataCredito.setError(validacao);
            }else if(validacao.contains("credito")){
                edValorCredito.setError(validacao);
            }
        } else {

            Double valor = Double.parseDouble(valorNumerico);
            Date data = new Date(valorData);

            if (creditocontroller.salvarCredito(valor, data) > 0) {
                Toast.makeText(getContext(),
                        "Credito cadastrado com sucesso!!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Credito, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}