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
import android.widget.Spinner;
import android.widget.Toast;
import com.example.controlenamao.Adapter.CreditoAdapter;
import com.example.controlenamao.Adapter.FreteAdapter;
import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.CreditoController;
import com.example.controlenamao.controller.FreteController;
import com.example.controlenamao.controller.HomeController;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.masks.MaskedData;
import com.example.controlenamao.model.Frete;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;
import java.util.ArrayList;
import java.util.Date;

public class CreditFragment extends Fragment {

    private EditText edValorCredito;
    private EditText edDataCredito;
    private Button btCadastroCredito;
    private Button btHome;

    private ListView lv;

    private CreditoController creditocontroller;
    private Spinner spinnerVeiculos;
    private Spinner spinnerFretes;

    private ArrayList<Veiculo> listaVeiculos;
    private ArrayList<Frete> listaFretes;
    private ArrayList<Movimentacao> listaCredito;

    private HomeController hc;
    private VeiculoController vc;
    private FreteController fc;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        creditocontroller = new CreditoController(getContext());

        btCadastroCredito = getView().findViewById(R.id.btCadastroCredito);
        edValorCredito = getView().findViewById(R.id.edValorCredito);
        edDataCredito = getView().findViewById(R.id.edDataCredito);
        spinnerVeiculos = getView().findViewById(R.id.spVeiculos);
        spinnerFretes = getView().findViewById(R.id.spFretes);
        btHome = getView().findViewById(R.id.btHome);

        spinnerVeiculos = getActivity().findViewById(R.id.spVeiculos);
        spinnerFretes = getActivity().findViewById(R.id.spFretes);
        vc =  new VeiculoController(getContext());
        fc =  new FreteController(getContext());
        listaVeiculos = vc.retornarTodosVeiculos();
        listaFretes = fc.retornarTodosFretes();
        VeiculoAdapter vcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        FreteAdapter fcAdapter = new FreteAdapter(this.getContext(), listaFretes);
        vcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVeiculos.setAdapter(vcAdapter);
        spinnerFretes.setAdapter(fcAdapter);

        lv = getActivity().findViewById(R.id.lvListaC);
        hc =  new HomeController(getContext());
        listaCredito = hc.retornarTodosCreditos();

        CreditoAdapter cAdapter = new CreditoAdapter(this.getContext(), listaCredito);
        cAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lv.setAdapter(cAdapter);

//      MÁSCARA PARA O CAMPO DATA
        MaskedData.addDateMask(edDataCredito);

        btCadastroCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCredito();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                adb.setTitle("Deletar?");
                adb.setMessage("Deseja remover esse crédito? ");
                //PEGANDO O ID DA LISTA NÃO DO BANCO
                cAdapter.getItem((int) id);
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        hc.apagarCredito(listaCredito.get(position));

                        listaCredito.remove(position);

                        atualizaLista();
                    }});
                adb.show();

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
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }

    private void salvarCredito() {

        try {

            //Se vir em branco assume como zero
            String valorNumerico = edValorCredito.getText() != null && !edValorCredito.getText().toString().isEmpty() ? edValorCredito.getText().toString() : "0";
            String valorData = edDataCredito.getText() != null && !edDataCredito.getText().toString().isEmpty() ? edDataCredito.getText().toString() : "";
            Veiculo veiculo = null;

            //Validação para spinner veiculos(sem testar, mas não quebra)
            if (spinnerVeiculos.getSelectedItem() != null) {
                veiculo = (Veiculo) spinnerVeiculos.getSelectedItem();
            }

            Frete frete = null;

            //Validação para spinner veiculos(sem testar, mas não quebra)
            if (spinnerFretes.getSelectedItem() != null) {
                frete = (Frete) spinnerFretes.getSelectedItem();
            }


            String validacao = creditocontroller.validaCredito(
                    valorNumerico,
                    valorData,
                    veiculo,
                    frete);

            if (!validacao.equals("")) {

                    Toast.makeText(getContext(),
                            validacao,
                            Toast.LENGTH_LONG).show();

            } else {

                Double valor = Double.parseDouble(valorNumerico);
                Date data = new Date(valorData);

                if (creditocontroller.salvarCredito(valor, data, veiculo, frete) > 0) {
                    Toast.makeText(getContext(),
                            "Credito cadastrado com sucesso!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),
                            "Erro ao cadastrar Credito, verifique LOG.",
                            Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void atualizaLista(){
        CreditoAdapter adapter = new CreditoAdapter(this.getContext(), listaCredito);
        lv.setAdapter(adapter);
    }
}