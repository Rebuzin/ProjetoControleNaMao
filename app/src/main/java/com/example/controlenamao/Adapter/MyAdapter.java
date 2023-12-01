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

    FaturamentoFragment fat = new FaturamentoFragment();
    CreditFragment cred = new CreditFragment();
    DebitFragment deb = new DebitFragment();

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
                return fat;
            case 1:
                return cred;
            case 2:
                return deb;
            default:
                return fat;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}