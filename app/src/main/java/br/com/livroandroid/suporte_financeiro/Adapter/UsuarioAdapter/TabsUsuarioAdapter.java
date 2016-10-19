package br.com.livroandroid.suporte_financeiro.Adapter.UsuarioAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.CadastrarUsuario;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.ExcluirUsuario;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.VisualizarUsuarios;
import br.com.livroandroid.suporte_financeiro.R;

/**
 * Created by Rrafael on 17/10/2016
 */

public class TabsUsuarioAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsUsuarioAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.CadastrarUsuario);
        } else if (position == 1) {
            return context.getString(R.string.ExcluirUsuario);
        }
        return context.getString(R.string.VisualizarUsuario);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new CadastrarUsuario();
        } else if (position == 1) {
            f = new ExcluirUsuario();
        } else {
            f = new VisualizarUsuarios();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
