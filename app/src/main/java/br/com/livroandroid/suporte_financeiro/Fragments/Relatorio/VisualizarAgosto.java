package br.com.livroandroid.suporte_financeiro.Fragments.Relatorio;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Relatorio;
import br.com.livroandroid.suporte_financeiro.domain.Services.InvestimentoService;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizarAgosto extends Fragment {


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
    private br.com.livroandroid.suporte_financeiro.domain.Services.DespesaService DespesaService;
    private InvestimentoService investimentoService = new InvestimentoService();
    private List<Despesa> despesas = null;
    private List<Investimento> investimentos = null;
    public VisualizarAgosto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizar_agosto, container, false);
        TextQ1 = (TextView) view.findViewById(R.id.textViewQuant1Agosto);
        TextVdespesa = (TextView) view.findViewById(R.id.textViewVT1Agos1);
        TextQ2 = (TextView) view.findViewById(R.id.textViewVT3Agosto);
        TextVdespesa2 = (TextView) view.findViewById(R.id.textViewQUT);
        TextQInvestimento = (TextView) view.findViewById(R.id.vT18Agosto);
        TextValorInvestimento = (TextView) view.findViewById(R.id.textViewqtd10Agosto);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            Calendar hj = Calendar.getInstance();
            despesas = this.DespesaService.getDespesas(getContext());
            for (int i =0; i < despesas.size(); i++) {
                Despesa d = despesas.get(i);
                if (d.getDataVencimento().get(Calendar.MONTH) == Calendar.AUGUST) {
                    if (d.getImportancia() == Importancia.LAZER.getDescricao() || d.getImportancia().equals("lazer") || d.getImportancia().equals("LAZER")) {
                        vt2 += d.getValorDespesa().doubleValue();
                        cont2 += i;
                    } else {
                        vt1 += d.getValorDespesa().doubleValue();
                        cont3 += i;
                    }
                }
            }
            investimentos = investimentoService.getInvestimentos(getContext());
            for (int i=0; i <investimentos.size(); i++) {
                Investimento investimento = investimentos.get(i);
                if(investimento.getVencimentoInvestimento().get(Calendar.MONTH) == Calendar.AUGUST) {
                    vt3 += investimento.getValorInvestimento().doubleValue();
                    cont1 += i;
                }
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
    }
}
