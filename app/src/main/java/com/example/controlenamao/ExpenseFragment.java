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
import android.widget.Toast;

import com.example.controlenamao.Adapter.GastoAdapter;
import com.example.controlenamao.controller.GastoController;
import com.example.controlenamao.model.Gasto;

import java.util.ArrayList;

public class ExpenseFragment extends Fragment {

    private EditText edExpense;
    private Button btCadastroGasto;
    private Button btHome;
    private ListView lvGasto;
    private GastoController gastocontroller;

    private GastoController gc;
    private ArrayList<Gasto> listaGastos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gastocontroller = new GastoController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroGasto = getView().findViewById(R.id.btCadastroGasto);
        btHome = getView().findViewById(R.id.btHome);
        edExpense = getView().findViewById(R.id.edExpense);

        lvGasto = getView().findViewById(R.id.lvGasto);

        lvGasto = getActivity().findViewById(R.id.lvGasto);
        gc =  new GastoController(getContext());
        listaGastos = gc.retornarTodosGastos();

        GastoAdapter gcAdapter = new GastoAdapter(this.getContext(), listaGastos);
        gcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lvGasto.setAdapter(gcAdapter);

        btCadastroGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarGasto();
            }
        });

        atualizaLista();

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
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    private void salvarGasto() {
        String validacao = gastocontroller.validaGasto(
                edExpense.getText().toString());

        if (!validacao.equals("")) {
            if (validacao.contains("gasto")) {
                edExpense.setError(validacao);
            }
        } else {
            if (gastocontroller.salvarGasto(
                    edExpense.getText().toString()) > 0) {
                Toast.makeText(getContext(),
                        "Gasto cadastrado com sucesso!!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Gasto, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    private void atualizaLista() {
        GastoAdapter adapter = new GastoAdapter(this.getContext(), listaGastos);
        lvGasto.setAdapter(adapter);
    }
}