package br.com.livroandroid.suporte_financeiro.Activity.InvestimentoActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.suporte_financeiro.Activity.Base_Activity;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.TabsInvestimentosFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class InvestimentoActivityFragment extends Base_Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento_fragment);
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Investimentos");
        if (savedInstanceState == null) {
            TabsInvestimentosFragment fragtabs = new TabsInvestimentosFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.InvestimentoFragment,fragtabs).commit();
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
