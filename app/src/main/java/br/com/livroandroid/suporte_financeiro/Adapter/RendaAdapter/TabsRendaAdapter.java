package br.com.livroandroid.suporte_financeiro.Adapter.RendaAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.suporte_financeiro.Fragments.Renda.CadastrarRenda;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.ExcluirRenda;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.VisualizarRenda;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * Created by Rrafael on 19/10/2016.
 */

public class TabsRendaAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabsRendaAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.CadastrarRenda);
        } else {
            return context.getString(R.string.VisualizarRenda);
        }

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if(position == 0) {
            f = new CadastrarRenda();
        } else {
            f = new VisualizarRenda();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
