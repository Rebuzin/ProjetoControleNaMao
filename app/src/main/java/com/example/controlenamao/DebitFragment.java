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

import com.example.controlenamao.controller.DebitoController;

import java.util.Date;

public class DebitFragment extends Fragment {

    private EditText edValorDebito;
    private EditText edDataDebito;
    private Button btCadastroDebito;
    private DebitoController debitocontroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        debitocontroller = new DebitoController(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btCadastroDebito = getView().findViewById(R.id.btCadastroDebito);
        edValorDebito = getView().findViewById(R.id.edValorDebito);
        edDataDebito = getView().findViewById(R.id.edDataDebito);

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

        //Se vir em branco assume como zero
        String valorNumerico = edValorDebito.getText() != null && !edValorDebito.getText().toString().isEmpty() ? edValorDebito.getText().toString() : "0";
        String valorData = edDataDebito.getText() != null && !edDataDebito.getText().toString().isEmpty() ? edDataDebito.getText().toString() : "";

        String validacao = debitocontroller.validaDebito(
                valorNumerico,
                valorData);

        if (!validacao.equals("")) {
            if (validacao.contains("debito")) {
                edValorDebito.setError(validacao);
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
    }
}
