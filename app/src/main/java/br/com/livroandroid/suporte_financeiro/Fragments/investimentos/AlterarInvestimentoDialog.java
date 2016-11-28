package br.com.livroandroid.suporte_financeiro.Fragments.investimentos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;

/**
 * Created by Rrafael on 27/11/2016.
 */

public class AlterarInvestimentoDialog extends DialogFragment {
    private Callback callback;
    private EditText NomeInvestimento;
    private EditText ValorInvestimento;
    private Calendar calendar;
    private CalendarView calendarView;
    private Button btnalterar;
    private Investimento investimento;
    public interface Callback {
        void OnUpdateInvestimento(Investimento investimento);
    }

    public static void show(FragmentManager fm, Investimento investimento, Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("editar_despesa");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AlterarInvestimentoDialog frag = new AlterarInvestimentoDialog();
        frag.callback = callback;
        Bundle args = new Bundle();
        args.putParcelable("investimento",investimento);
        frag.setArguments(args);
        frag.show(ft,"editar_investimento");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        int width = getResources().getDimensionPixelSize(R.dimen.popup1_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup1_height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_investimentos,container,false);
        NomeInvestimento = (EditText) view.findViewById(R.id.Edit_Investimento);
        ValorInvestimento = (EditText) view.findViewById(R.id.EditValorInvestimento);
        btnalterar = (Button) view.findViewById(R.id.btn_investimento);
        calendarView =(CalendarView) view.findViewById(R.id.calendarViewInvestimento);
        this.investimento = getArguments().getParcelable("investimento");
        if(investimento != null) {
            NomeInvestimento.setText(investimento.getNomeInvestimento());
        }
        btnalterar.setOnClickListener(onClickAtualizar());
        return view;
    }


    private View.OnClickListener onClickAtualizar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnalterar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendar = new GregorianCalendar();
                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                Toast.makeText(getContext(),"ano:"+year+" mes "+month+" dia"+dayOfMonth+" ",Toast.LENGTH_SHORT).show();
                            }
                        });
                        String novoNome = NomeInvestimento.getText().toString();
                        String novoValor = ValorInvestimento.getText().toString();

                        if (novoNome == null || novoNome.equals("") || novoNome.trim().length() == 0) {
                            NomeInvestimento.setText("Erro no nome!");
                        }

                        if (novoValor == null || novoValor.equals("") || novoValor.trim().length() == 0) {
                            ValorInvestimento.setText("Erro no valor!");
                        }

                        Context context = getView().getContext();
                        Investimento investimento = new Investimento();

                        if (calendar.getTime() != null) {
                            investimento.setVencimentoInvestimento(calendar);
                        }
                        investimento.setNomeInvestimento(novoNome);
                        investimento.setValorInvestimento(BigDecimal.valueOf(Double.parseDouble(novoValor)));

                        if(callback != null) {
                            callback.OnUpdateInvestimento(investimento);
                        }
                        dismiss();
                    }
                });
            }
        };
    }
}
