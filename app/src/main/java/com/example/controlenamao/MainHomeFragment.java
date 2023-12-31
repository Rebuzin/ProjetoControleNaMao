package com.example.controlenamao;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controlenamao.Adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainHomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        if(savedInstanceState == null) {
            tabLayout = getView().findViewById(R.id.tabLayout1);
            viewPager = getView().findViewById(R.id.viewPager1);
            tabLayout.removeAllTabs();

            atualizaTabs();

            viewPager.setCurrentItem(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_home, container, false);
    }

    public void atualizaTabs(){
        //Adicionando as tabs
        tabLayout.addTab(tabLayout.newTab().setText("Faturamento"));
        tabLayout.addTab(tabLayout.newTab().setText("Crédito"));
        tabLayout.addTab(tabLayout.newTab().setText("Débito"));

        FaturamentoFragment fat = new FaturamentoFragment();

        final MyAdapter adapter = new MyAdapter(this.getContext(),
                getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}