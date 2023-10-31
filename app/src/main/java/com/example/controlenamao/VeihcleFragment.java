package com.example.controlenamao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.controller.VeiculoController;

public class VeihcleFragment extends Fragment {

    private EditText edRenamed;
    private Button btCadastroVeiculo;
    private VeiculoController veiculocontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        veiculocontroller = new VeiculoController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroVeiculo = getView().findViewById(R.id.btCadastroVeiculo);
        edRenamed = getView().findViewById(R.id.edRenamed);

        btCadastroVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarVeiculo();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veihcle, container, false);

    }

    private void salvarVeiculo() {
        String validacao = veiculocontroller.validaVeiculo(
                edRenamed.getText().toString());

        if (!validacao.equals("")) {
            if (validacao.contains("placa")) {
                edRenamed.setError(validacao);
            }
        } else {
            if (veiculocontroller.salvarVeiculo(
                    edRenamed.getText().toString()) > 0) {
                Toast.makeText(getContext(),
                        "Veiculo cadastrado com sucesso!!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Veiculo, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}