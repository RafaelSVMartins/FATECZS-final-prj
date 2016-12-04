package br.com.livroandroid.suporte_financeiro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.livroandroid.suporte_financeiro.Activity.DespesaActivity.DespesaActivityFragment;
import br.com.livroandroid.suporte_financeiro.Activity.InvestimentoActivities.InvestimentoActivityFragment;
import br.com.livroandroid.suporte_financeiro.Activity.ListaActivity.ListaOperacoes;
import br.com.livroandroid.suporte_financeiro.Activity.RendaActivities.RendaActivityFragment;
import br.com.livroandroid.suporte_financeiro.Fragments.AboutDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.FinancasFragment;
import br.com.livroandroid.suporte_financeiro.R;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends Base_Activity {

    private Button despesas;
    private Button rendas;
    private Button investimento;
    private Button lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        setUpDrawer();
        SQLiteStudioService.instance().start(this);

        despesas = (Button) findViewById(R.id.button);
        rendas = (Button) findViewById(R.id.button2);
        investimento = (Button) findViewById(R.id.button4);
        lista = (Button) findViewById(R.id.button5);

        despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DespesaActivityFragment.class);
                startActivity(intent);
            }
        });

        rendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RendaActivityFragment.class);
                startActivity(intent);
            }
        });

        investimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InvestimentoActivityFragment.class);
                startActivity(intent);
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListaOperacoes.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(this,"Clicou no sobre",Toast.LENGTH_SHORT).show();
            AboutDialog.ShowAbout(getSupportFragmentManager());
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }
}

