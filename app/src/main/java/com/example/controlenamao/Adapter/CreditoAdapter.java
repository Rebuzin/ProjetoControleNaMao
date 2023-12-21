package com.example.controlenamao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;

import java.util.List;

public class CreditoAdapter extends ArrayAdapter<Movimentacao> {
    public CreditoAdapter(Context context, List<Movimentacao> movimentacao) {
        super(context, 0, movimentacao);
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
        Movimentacao movimentacao = getItem(position);

        if (movimentacao != null) {
            textView.setText((int) movimentacao.getValor());
        }

        return convertView;
    }

}
