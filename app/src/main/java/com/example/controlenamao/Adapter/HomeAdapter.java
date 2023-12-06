package com.example.controlenamao.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controlenamao.R;
import com.example.controlenamao.model.Veiculo;
import com.example.controlenamao.model.vo.HomeVo;

import java.util.List;

public class HomeAdapter extends ArrayAdapter<HomeVo> {
    private String [] colors = new String[10];

    public HomeAdapter(Context context, List<HomeVo> list) {
        super(context, R.layout.list_item_duplo, list);

        colors[0] = "#AF4646";
        colors[1] = "#403A3A";
        colors[2] = "#FFC107";
        colors[3] = "#00008B";
        colors[4] = "#2F4F4F";
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
            convertView = inflater.inflate(R.layout.list_item_duplo, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        TextView text2View = convertView.findViewById(android.R.id.text2);
        HomeVo obj = getItem(position);

        if (obj != null) {
            textView.setText(obj.getTipo());
            text2View.setText(String.format("%.2f", obj.getValor()));

            if (obj.getTipo().equals("Lucro Final")) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                text2View.setTextColor(Color.parseColor("#18E622"));
                text2View.setTypeface(text2View.getTypeface(), Typeface.BOLD);
            } else {
                text2View.setTextColor(Color.parseColor(colors[position]));
                text2View.setText(String.format("%.2f", obj.getValor()));
            }
        }

        return convertView;
    }
}

