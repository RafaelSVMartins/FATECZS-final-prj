package br.com.livroandroid.suporte_financeiro.Activity.ListaActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.suporte_financeiro.Activity.Base_Activity;
import br.com.livroandroid.suporte_financeiro.Fragments.Lista.ListaFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class VisualizarLista extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lista Produtos");

        if (savedInstanceState == null) {
            ListaFragment lista =  new ListaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoProdutos,lista).commit();
        }

    }

}
