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

import com.example.controlenamao.controller.FreteController;

public class FreightFragment extends Fragment {

    private EditText edFreight;
    private Button btCadastroFrete;
    private FreteController fretecontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fretecontroller = new FreteController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroFrete = getView().findViewById(R.id.btCadastroFrete);
        edFreight = getView().findViewById(R.id.edFreight);

        btCadastroFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFrete();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_freight, container, false);

    }

    private void salvarFrete() {
        String validacao = fretecontroller.validaFrete(
                edFreight.getText().toString());

        if (!validacao.equals("")) {
            if (validacao.contains("frete")) {
                edFreight.setError(validacao);
            }
        } else {
            if (fretecontroller.salvarFrete(
                    edFreight.getText().toString()) > 0) {
                Toast.makeText(getContext(),
                        "Frete cadastrado com sucesso!!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Frete, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}