package br.com.livroandroid.suporte_financeiro.Adapter.DespeesaAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.suporte_financeiro.Fragments.despesa.CadastrarDespesa;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.ExcluirDespesa;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.VisualizarDespesas;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * Created by Rrafael on 19/10/2016.
 */

public class TabsDespesaAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabsDespesaAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.CadastrarDespesa);
        } else {
            return context.getString(R.string.VisualizarDespesa);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new CadastrarDespesa();
        } else {
            f = new VisualizarDespesas();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
