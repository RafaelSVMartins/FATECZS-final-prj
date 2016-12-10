package br.com.livroandroid.suporte_financeiro.Activity.Relatorios;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarAgosto;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarDezembro;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarJulho;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarJunho;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarNovembro;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarOutubro;
import br.com.livroandroid.suporte_financeiro.Fragments.Relatorio.VisualizarSetembro;
import br.com.livroandroid.suporte_financeiro.R;

public class RelatorioMensal extends AppCompatActivity {

    private Button BtnJunho;
    private Button BtnJulho;
    private Button BtnAgosto;
    private Button BtnSetembro;
    private Button BtnOutubro;
    private Button BtnNovembro;
    private Button BtnDezembro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_mensal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BtnJunho = (Button) findViewById(R.id.btnJunho);
        BtnJulho = (Button) findViewById(R.id.btnJulho);
        BtnAgosto = (Button)findViewById(R.id.btnagosto);
        BtnSetembro = (Button) findViewById(R.id.btnSetembro);
        BtnOutubro = (Button) findViewById(R.id.btnOutubro);
        BtnNovembro = (Button) findViewById(R.id.btnNovembro);
        BtnDezembro = (Button) findViewById(R.id.btnDezembro);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BtnJunho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarJunho vj = new VisualizarJunho();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,vj).commit();
            }
        });

        BtnJulho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarJulho vJl = new VisualizarJulho();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,vJl).commit();
            }
        });
        BtnAgosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarAgosto vA = new VisualizarAgosto();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,vA).commit();
            }
        });
        BtnSetembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarSetembro sT = new VisualizarSetembro();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,sT).commit();
            }
        });
        BtnOutubro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarOutubro sO = new VisualizarOutubro();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,sO).commit();
            }
        });
        BtnNovembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarNovembro Nv = new VisualizarNovembro();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,Nv).commit();
            }
        });
        BtnDezembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarDezembro Nd = new VisualizarDezembro();
                getSupportFragmentManager().beginTransaction().replace(R.id.FragmentoMensal,Nd).commit();
            }
        });
    }
}
