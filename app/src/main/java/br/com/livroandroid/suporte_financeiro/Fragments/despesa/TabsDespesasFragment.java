package br.com.livroandroid.suporte_financeiro.Fragments.despesa;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.suporte_financeiro.Adapter.DespeesaAdapter.TabsDespesaAdapter;
import br.com.livroandroid.suporte_financeiro.Adapter.TabsFinancasAdapter;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsDespesasFragment extends Fragment {


    public TabsDespesasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs_despesas, container, false);
        ViewPager viewPager = (ViewPager)  view.findViewById(R.id.viewPagerDespesas);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsDespesaAdapter(getChildFragmentManager(), getContext()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutDespesas);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(),R.color.white);
        tabLayout.setTabTextColors(cor,cor);
        return view;
    }

}
