package com.example.controlenamao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controlenamao.Adapter.FreteAdapter;
import com.example.controlenamao.Adapter.FreteAdapter;
import com.example.controlenamao.controller.FreteController;
import com.example.controlenamao.model.Frete;

import java.util.ArrayList;

public class FreightFragment extends Fragment {

    private EditText edFreight;
    private Button btCadastroFrete;
    private Button btHome;
    private ListView lvFrete;
    private FreteController fretecontroller;

    private FreteController fc;
    private ArrayList<Frete> listaFretes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fretecontroller = new FreteController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroFrete = getView().findViewById(R.id.btCadastroFrete);
        btHome = getView().findViewById(R.id.btHome);
        edFreight = getView().findViewById(R.id.edFreight);
        lvFrete = getView().findViewById(R.id.lvFrete);

        lvFrete = getActivity().findViewById(R.id.lvFrete);
        fc =  new FreteController(getContext());
        listaFretes = fc.retornarTodosFretes();

        FreteAdapter fcAdapter = new FreteAdapter(this.getContext(), listaFretes);
        fcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lvFrete.setAdapter(fcAdapter);

        btCadastroFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFrete();
            }
        });

        lvFrete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                adb.setTitle("Deletar?");
                adb.setMessage("Deseja remover esse tipo de frete? ");
                final Long positionToRemove = id;
                fcAdapter.getItem((int) id);
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fretecontroller.apagarFrete(listaFretes.get(position));
                        fcAdapter.notifyDataSetChanged();
                        listaFretes.remove(position);
                        atualizaLista();
                    }});
                adb.show();
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
                listaFretes = fc.retornarTodosFretes();
                FreteAdapter gcAdapter = new FreteAdapter(this.getContext(), listaFretes);
                gcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                lvFrete.setAdapter(gcAdapter);
                edFreight.setText("");
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Frete, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
        atualizaLista();
    }

    private void atualizaLista(){
        FreteAdapter adapter = new FreteAdapter(this.getContext(), listaFretes);
        lvFrete.setAdapter(adapter);
    }
//    private void excluirFrete(){
//        fretecontroller.apagarFrete(lvFrete.getSelectedItemId());
//    }
}