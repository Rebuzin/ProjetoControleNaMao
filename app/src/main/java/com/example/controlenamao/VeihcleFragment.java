package com.example.controlenamao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.dao.VeiculoDao;
import com.example.controlenamao.masks.MaskedRenamed;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;

public class VeihcleFragment extends Fragment {

    private EditText edRenamed;
    private Button btCadastroVeiculo;
    private Button btHome;
    private ListView lvVeiculo;
    private VeiculoController veiculocontroller;

    private VeiculoController vc;
    private ArrayList<Veiculo> listaVeiculos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        veiculocontroller = new VeiculoController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroVeiculo = getView().findViewById(R.id.btCadastroVeiculo);
        edRenamed = getView().findViewById(R.id.edRenamed);
        btHome = getView().findViewById(R.id.btHome);

        lvVeiculo = getView().findViewById(R.id.lvVeiculo);

        lvVeiculo = getActivity().findViewById(R.id.lvVeiculo);
        vc =  new VeiculoController(getContext());
        listaVeiculos = vc.retornarTodosVeiculos();

        VeiculoAdapter gcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        gcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lvVeiculo.setAdapter(gcAdapter);

//        MÁSCARA PARA O CAMPO PLACA
        MaskedRenamed.addRenamedMask(edRenamed);

        btCadastroVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarVeiculo();
                atualizaLista();
            }
        });

        lvVeiculo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Você deseja deletar " + listaVeiculos.get(position) + "da lista?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                excluirVeiculo(position);
                                gcAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                return false;
            }
        });
        lvVeiculo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                adb.setTitle("Deletar?");
                adb.setMessage("Deseja remover esse veículo? ");
                //PEGANDO O ID DA LISTA NÃO DO BANCO
                final Long positionToRemove = id;
                gcAdapter.getItem((int) id);
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        veiculocontroller.apagarVeiculo(listaVeiculos.get(position));

                        listaVeiculos.remove(position);

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
                listaVeiculos = vc.retornarTodosVeiculos();
                VeiculoAdapter gcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
                gcAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                lvVeiculo.setAdapter(gcAdapter);
                edRenamed.setText("");
            } else {
                Toast.makeText(getContext(),
                        "Erro ao cadastrar Veiculo, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    private void atualizaLista(){
        VeiculoAdapter adapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        lvVeiculo.setAdapter(adapter);
    }
    private void excluirVeiculo(){
        VeiculoAdapter adapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        Long id = lvVeiculo.getSelectedItemId();
        adapter.getItemId(id.intValue());
//       veiculocontroller.apagarVeiculo(adapter);
    }


}