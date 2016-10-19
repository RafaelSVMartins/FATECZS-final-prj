package br.com.livroandroid.suporte_financeiro.Adapter.InvestimentoAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.CadastrarInvestimentos;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.ExcluirInvestimentos;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.VisualizarInvestimentos;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * Created by Rrafael on 19/10/2016.
 */

public class TabsInvestimentoAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabsInvestimentoAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new CadastrarInvestimentos();
        } else if (position == 1) {
            f = new VisualizarInvestimentos();
        } else  {
            f = new ExcluirInvestimentos();
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.CadastrarInvestimento);
        } else if (position == 1) {
            return context.getString(R.string.VisualizarInvestimento);
        }
        return context.getString(R.string.ExcluirInvestimento);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
