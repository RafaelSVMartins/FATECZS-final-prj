package br.com.livroandroid.suporte_financeiro.Activity.Relatorios;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Relatorio;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Services.DespesaService;
import br.com.livroandroid.suporte_financeiro.domain.Services.InvestimentoService;
import br.com.livroandroid.suporte_financeiro.domain.Services.RendaService;

public class RelatorioAnual extends AppCompatActivity {

    private static final String TAG = "Relatórios";
    private TextView TextQ1;
    private TextView TextVdespesa;
    private TextView TextQ2;
    private TextView TextVdespesa2;
    private TextView TextQInvestimento;
    private TextView TextValorInvestimento;
    private Double vt2 = Double.valueOf(0);
    private Double vt3 = Double.valueOf(0);
    private Double vt1 = Double.valueOf(0);
    private int cont1 = 0;
    private int cont2 = 1;
    private int cont3 = 1;
    private Relatorio relatorio;
    private DespesaService DespesaService;
    private InvestimentoService investimentoService = new InvestimentoService();
    private List<Despesa> despesas = null;
    private List<Investimento> investimentos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_anual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextQ1 = (TextView)findViewById(R.id.textVQuantidade);
        TextVdespesa = (TextView)findViewById(R.id.textVTotal3);
        TextQ2 = (TextView) findViewById(R.id.textVImQuant);
        TextVdespesa2 = (TextView) findViewById(R.id.textVIm);
        TextQInvestimento = (TextView) findViewById(R.id.textInvQuant);
        TextValorInvestimento = (TextView) findViewById(R.id.textInvTotal);

        try {
            Calendar hj = Calendar.getInstance();
            despesas = this.DespesaService.getDespesas(getBaseContext());
            for (int i =0; i < despesas.size(); i++) {
                Despesa d = despesas.get(i);
                if (d.getImportancia() == Importancia.LAZER.getDescricao() || d.getImportancia().equals("lazer") || d.getImportancia().equals("LAZER")) {
                    vt2 += d.getValorDespesa().doubleValue();
                    cont2 += i;
                } else {
                    vt1 += d.getValorDespesa().doubleValue();
                    cont3 += i;
                }
            }
            investimentos = investimentoService.getInvestimentos(getBaseContext());
            for (int i=0; i <investimentos.size(); i++) {
                Investimento investimento = investimentos.get(i);
                vt3 += investimento.getValorInvestimento().doubleValue();
                cont1 += i;
            }
            TextQ1.setText(String.valueOf(cont2));
            TextQ2.setText(String.valueOf(cont3));
            TextQInvestimento.setText(String.valueOf(cont1));
            TextVdespesa.setText(vt2.toString());
            TextVdespesa2.setText(vt1.toString());
            TextValorInvestimento.setText(vt3.toString());

        } catch (IOException e) {
            Log.d(TAG,"Erro na visualização do Relatório ("+e.getMessage()+"). ");
        }
        relatorio = new Relatorio();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
