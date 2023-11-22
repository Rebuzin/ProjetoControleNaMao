package com.example.controlenamao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controlenamao.model.Gasto;

import java.util.List;

public class GastoAdapter extends ArrayAdapter<Gasto> {
    public GastoAdapter(Context context, List<Gasto> gastos) {
        super(context, 0, gastos);
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
        Gasto gasto = getItem(position);

        if (gasto != null) {
            textView.setText(gasto.getName());
        }

        return convertView;
    }
}
