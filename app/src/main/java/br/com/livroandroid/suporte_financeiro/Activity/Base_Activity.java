package br.com.livroandroid.suporte_financeiro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.livroandroid.suporte_financeiro.Activity.DespesaActivity.DespesaActivityFragment;
import br.com.livroandroid.suporte_financeiro.Activity.InvestimentoActivities.InvestimentoActivityFragment;
import br.com.livroandroid.suporte_financeiro.Activity.RendaActivities.RendaActivityFragment;
import br.com.livroandroid.suporte_financeiro.Activity.UsuarioActivities.UsuarioActivityFragment;
import br.com.livroandroid.suporte_financeiro.Fragments.ContatoFinancas;
import br.com.livroandroid.suporte_financeiro.Fragments.FinancasFragment;
import br.com.livroandroid.suporte_financeiro.Fragments.FinancasTabsFragments;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.TabsRendaFragments;
import br.com.livroandroid.suporte_financeiro.Fragments.despesa.TabsDespesasFragment;
import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.TabsInvestimentosFragment;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.TabsUsuariosFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class Base_Activity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected FloatingActionButton fab;
    protected ActionBarDrawerToggle toggle;

    protected  void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_);
        setToolbar();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    protected void setUpDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.frag1) {
                    //replaFragment(new TabsUsuariosFragment());
                    Intent intent = new Intent(getBaseContext(), UsuarioActivityFragment.class);
                    startActivity(intent);
                } else if (id == R.id.frag2) {
                    //replaFragment(new TabsRendaFragments());
                    Intent intent = new Intent(getBaseContext(), RendaActivityFragment.class);
                    startActivity(intent);
                } else if (id == R.id.frag3) {
                    //replaFragment(new TabsInvestimentosFragment());
                    Intent intent = new Intent(getBaseContext(), InvestimentoActivityFragment.class);
                    startActivity(intent);
                } else if (id == R.id.frag4) {
                    //replaFragment(new TabsDespesasFragment());
                    Intent intent = new Intent(getBaseContext(), DespesaActivityFragment.class);
                    startActivity(intent);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void replaFragment(Fragment frag) {
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,frag,"TAG").commit();
    }
}
