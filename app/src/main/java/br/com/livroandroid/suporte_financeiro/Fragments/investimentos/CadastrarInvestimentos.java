package br.com.livroandroid.suporte_financeiro.Fragments.investimentos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Services.InvestimentoService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarInvestimentos extends Fragment {
    private EditText NomeInvestimento;
    private EditText ValorInvestimento;
    private Button cadastrarInvestimento;
    private CalendarView calendarView;
    private Calendar calendar;
    private Investimento investimento;
    public CadastrarInvestimentos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_investimentos, container, false);
        NomeInvestimento = (EditText) view.findViewById(R.id.Edit_Investimento);
        ValorInvestimento = (EditText) view.findViewById(R.id.EditValorInvestimento);
        cadastrarInvestimento = (Button) view.findViewById(R.id.btn_investimento);
        calendarView = (CalendarView) view.findViewById(R.id.calendarViewInvestimento);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendar = Calendar.getInstance();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                Toast.makeText(getContext(),"ano:"+year+" mes "+month+" dia"+dayOfMonth+" ",Toast.LENGTH_SHORT).show();
            }
        });

        cadastrarInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                investimento = new Investimento();
                investimento.setNomeInvestimento(NomeInvestimento.getText().toString());
                investimento.setVencimentoInvestimento(calendar);
                investimento.setValorInvestimento(BigDecimal.valueOf(Double.parseDouble((ValorInvestimento.getText().toString()))));
                Usuario user = new Usuario();
                user.setId(2l);
                user.setNome("Juan");
                investimento.setUsuario(user);
                InvestimentoService.Salvar(getContext(),investimento);
            }
        });

    }
}
