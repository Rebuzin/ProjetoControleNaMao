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
                FaturamentoFragment homeFragment = new FaturamentoFragment();
                return homeFragment;
            case 1:
                CreditFragment creditFragment = new CreditFragment();
                return creditFragment;
            case 2:
                DebitFragment debitFragment = new DebitFragment();
                return debitFragment;
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