package com.example.controlenamao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controlenamao.R;
import com.example.controlenamao.model.Movimentacao;

import java.text.SimpleDateFormat;
import java.util.List;

public class DebitoAdapter extends ArrayAdapter<Movimentacao> {
    public DebitoAdapter(Context context, List<Movimentacao> movimentacao) {
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
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_debit, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        TextView textView2 = convertView.findViewById(android.R.id.text2);
        Movimentacao movimentacao = getItem(position);

        if (movimentacao != null) {

            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

//            textView.setText(dt1.format(movimentacao.getData()));
            textView2.setText(String.valueOf(movimentacao.getValor()));
        }

        return convertView;
    }

}