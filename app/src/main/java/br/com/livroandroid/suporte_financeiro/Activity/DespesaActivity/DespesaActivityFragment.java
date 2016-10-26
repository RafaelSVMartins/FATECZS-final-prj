package br.com.livroandroid.suporte_financeiro.Activity.DespesaActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.suporte_financeiro.Activity.Base_Activity;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.TabsDespesasFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class DespesaActivityFragment extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa_fragment);
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Despesas");

        if (savedInstanceState == null) {
            TabsDespesasFragment fragtab = new TabsDespesasFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FragmentoDespesas,fragtab).commit();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
