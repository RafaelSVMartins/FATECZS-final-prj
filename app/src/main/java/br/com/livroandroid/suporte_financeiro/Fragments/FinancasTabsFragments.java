package br.com.livroandroid.suporte_financeiro.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.suporte_financeiro.Adapter.TabsFinancasAdapter;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancasTabsFragments extends Fragment {

    private Toolbar toolbar;
    public FinancasTabsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view = (View) inflater.inflate(R.layout.fragment_financas_tabs_fragments, container, false);
        //toolbar =  (Toolbar) view.findViewById(R.id.toolbar);

        ViewPager viewPager = (ViewPager)  view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsFinancasAdapter(getContext(),getChildFragmentManager()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(),R.color.white);
        tabLayout.setTabTextColors(cor,cor);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
