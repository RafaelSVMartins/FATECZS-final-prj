package br.com.livroandroid.suporte_financeiro.Fragments.despesa;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;

/**
 * Created by Rrafael on 21/11/2016.
 */

public class AlterarDespesaDialog extends DialogFragment {
    private final List<String> impotantearray = new ArrayList<>();
    private Callback callback;
    private EditText NomeDespesa;
    private CalendarView calendarView;
    private Importancia imp;
    private Spinner spin;
    private EditText DespesaValor;
    private Button btnAlterar;
    private Despesa despesa;
    private String valorSelecionado;
    Calendar data;
    public interface Callback {
        void onUpdateDespesa(Despesa despesa);
    }

    public static void show(FragmentManager fm, Despesa despesa, Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("editar_despesa");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AlterarDespesaDialog frag = new AlterarDespesaDialog();
        frag.callback = callback;
        Bundle args = new Bundle();
        args.putParcelable("despesa",despesa);
        frag.setArguments(args);
        frag.show(ft,"editar_despesa");
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
        View view = inflater.inflate(R.layout.fragment_cadastrar_despesa,container,false);
        DespesaValor = (EditText) view.findViewById(R.id.editTextValor);
        spin = (Spinner) view.findViewById(R.id.spinner2);
        NomeDespesa = (EditText) view.findViewById(R.id.editTextDespesa);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        btnAlterar = (Button) view.findViewById(R.id.buttonDespesa);
        impotantearray.add("tranquilo");
        impotantearray.add("relevante");
        impotantearray.add("urgente");
        this.despesa = getArguments().getParcelable("despesa");
        if(despesa != null) {
            NomeDespesa.setText(despesa.getNomeDespesa());
        }
        btnAlterar.setOnClickListener(onClickAtualizar());
        return view;
    }

    private View.OnClickListener onClickAtualizar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, impotantearray);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spin.setAdapter(arrayAdapter);
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        valorSelecionado = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getContext(), "item selecionado: " + valorSelecionado, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                valorSelecionado = spin.getSelectedItem().toString();

                data = new GregorianCalendar();
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        data.set(year, month, dayOfMonth);
                        Toast.makeText(getContext(), "ano:" + year + " mes " + month + " dia" + dayOfMonth + " ", Toast.LENGTH_SHORT).show();
                    }
                });
                String novoNome = NomeDespesa.getText().toString();
                String novoValor = DespesaValor.getText().toString();
                String novaImporntancia;
                Long check;
                if (novoNome == null || novoNome.trim().length() == 0) {
                    NomeDespesa.setText("Nome Erro!");
                }

                if (novoValor == null || novoValor.trim().length() == 0) {
                    DespesaValor.setText("Valor Erro!");
                }

                Context context = getView().getContext();
                Despesa despesanova = new Despesa();

                if (valorSelecionado != null) {
                    novaImporntancia = valorSelecionado;
                } else {

                }

                check = calendarView.getDate();
                if (check != null) {
                    despesanova.setDataVencimento(data);
                }
                despesanova.setNomeDespesa(novoNome);
                despesanova.setValorDespesa(BigDecimal.valueOf(Double.parseDouble(novoValor)));
                if (callback != null) {
                    callback.onUpdateDespesa(despesanova);
                }
                dismiss();
            }
        };
    }
}
