package com.example.controlenamao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controlenamao.model.Frete;

import java.util.List;

public class FreteAdapter extends ArrayAdapter<Frete> {

    public FreteAdapter(Context context, List<Frete> fretes) {
        super(context, 0, fretes);
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
        Frete frete = getItem(position);

        if (frete != null) {
            textView.setText(frete.getName());
        }

        return convertView;
    }
}