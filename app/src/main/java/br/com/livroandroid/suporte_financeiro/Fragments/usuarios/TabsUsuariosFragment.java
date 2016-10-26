package br.com.livroandroid.suporte_financeiro.Fragments.usuarios;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter.TabsRendaAdapter;
import br.com.livroandroid.suporte_financeiro.Adapter.UsuarioAdapter.TabsUsuarioAdapter;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsUsuariosFragment extends Fragment {


    public TabsUsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs_usuarios, container, false);
        ViewPager viewPager = (ViewPager)  view.findViewById(R.id.viewPagerUsuario);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsUsuarioAdapter(getContext(), getChildFragmentManager()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutUsuarios);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(),R.color.white);
        tabLayout.setTabTextColors(cor,cor);
        return view;
    }

}
