package com.example.controlenamao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlenamao.Adapter.VeiculoAdapter;
import com.example.controlenamao.controller.VeiculoController;
import com.example.controlenamao.masks.MaskedData;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;

public class DialogFragment extends Fragment {

    private EditText edDataIn;
    private EditText edDataFn;
    private ImageButton btBuscar;
    private Spinner spinnerVeiculos;

    private ArrayList<Veiculo> listaVeiculos;

    private VeiculoController vc;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        edDataIn = getView().findViewById(R.id.edDataIn);
        edDataFn = getView().findViewById(R.id.edDataFn);
        spinnerVeiculos = getView().findViewById(R.id.spVeiculos);
        btBuscar = getView().findViewById(R.id.btBuscar);

        spinnerVeiculos = getActivity().findViewById(R.id.spVeiculos);
        vc =  new VeiculoController(getContext());
        listaVeiculos = vc.retornarTodosVeiculos();
        VeiculoAdapter vcAdapter = new VeiculoAdapter(this.getContext(), listaVeiculos);
        vcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVeiculos.setAdapter(vcAdapter);

//        MÁSCARA PARA O CAMPO DATA
        MaskedData.addDateMask(edDataIn);
        MaskedData.addDateMask(edDataFn);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.custom_dialog, container, false);
    }
}
