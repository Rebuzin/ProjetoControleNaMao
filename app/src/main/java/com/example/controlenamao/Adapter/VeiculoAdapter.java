package com.example.controlenamao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controlenamao.model.Veiculo;

import java.util.List;

public class VeiculoAdapter extends ArrayAdapter<Veiculo> {
    public VeiculoAdapter(Context context, List<Veiculo> veiculos) {
        super(context, 0, veiculos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Veiculo veiculo = getItem(position);

        if (veiculo != null) {
            textView.setText(veiculo.getRenamed());
        }

        return convertView;
    }
}

