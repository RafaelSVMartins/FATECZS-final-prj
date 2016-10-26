package br.com.livroandroid.suporte_financeiro.Activity.RendaActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.suporte_financeiro.Activity.Base_Activity;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.TabsRendaFragments;
import br.com.livroandroid.suporte_financeiro.R;

public class RendaActivityFragment extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renda_fragment);
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Renda");
        if (savedInstanceState == null) {
            TabsRendaFragments fragtabs = new TabsRendaFragments();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.RendaFragment,fragtabs).commit();
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
