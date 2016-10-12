package br.com.livroandroid.suporte_financeiro.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.livroandroid.suporte_financeiro.Fragments.AboutDialog;
import br.com.livroandroid.suporte_financeiro.Fragments.FinancasFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class MainActivity extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        setUpDrawer();
        replaFragment(new FinancasFragment());

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
}

