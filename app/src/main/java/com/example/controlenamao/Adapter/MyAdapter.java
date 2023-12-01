package com.example.controlenamao.Adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

import com.example.controlenamao.CreditFragment;
import com.example.controlenamao.DebitFragment;
import com.example.controlenamao.FaturamentoFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FaturamentoFragment();
            case 1:
                return new CreditFragment();
            case 2:
                return new DebitFragment();
            default:
                return null;
        }

    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}