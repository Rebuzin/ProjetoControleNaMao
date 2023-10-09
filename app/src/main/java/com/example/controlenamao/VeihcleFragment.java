package com.example.controlenamao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlenamao.controller.VeiculoController;

public class VeihcleFragment extends Fragment {

    private EditText edRenamed;
    private Button btCadastroVeiculo;
    private VeiculoController veiculocontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(!(btCadastroVeiculo == null)){
            salvarVeiculo();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veihcle, container, false);

    }



    private void salvarVeiculo() {
        String validacao = veiculocontroller.validaVeiculo(
                edRenamed.getText().toString());

        if (!validacao.equals("")) {
            if (validacao.contains("Placa")) {
                edRenamed.setError(validacao);
            }
        } else {
            if (veiculocontroller.salvarVeiculo(
                    edRenamed.getText().toString()) > 0) {
                Toast.makeText(getContext(), "Veiculo cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Erro ao cadastrar Veiculo, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }}