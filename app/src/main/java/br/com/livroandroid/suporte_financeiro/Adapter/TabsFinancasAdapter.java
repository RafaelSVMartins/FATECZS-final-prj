package br.com.livroandroid.suporte_financeiro.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.suporte_financeiro.Fragments.ContatoFinancas;
import br.com.livroandroid.suporte_financeiro.Fragments.FinancasFragment;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * Created by Rrafael on 02/10/2016.
 */

public class TabsFinancasAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabsFinancasAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.Frag1);
        } else if (position == 1) {
            return context.getString(R.string.Frag2);
        }
        return context.getString(R.string.Frag3);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if (position == 0) {
            f = new ContatoFinancas();
        } else if (position == 1) {
            f = new FinancasFragment();
        } else {
            f = new FinancasFragment();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
