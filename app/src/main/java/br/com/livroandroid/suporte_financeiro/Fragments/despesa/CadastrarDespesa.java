package br.com.livroandroid.suporte_financeiro.Fragments.despesa;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.List;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;
import br.com.livroandroid.suporte_financeiro.domain.Services.DespesaService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

import static android.R.layout.simple_spinner_item;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarDespesa extends Fragment {
    private final List<String> impotantearray = new ArrayList<>();
    private EditText nomeDespesa;
    private EditText valorDecimal;
    private EditText vencimentoDespesa;
    private CalendarView calendarView;
    private Spinner importancia;
    private String despimportancia;
    private Despesa despesa;
    private Button btncad;
    private DespesaService serviceD;
    Calendar data;
    private Importancia imp;
    public CadastrarDespesa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_despesa, container, false);
        nomeDespesa = (EditText) view.findViewById(R.id.editTextDespesa);
        valorDecimal = (EditText) view.findViewById(R.id.editTextValor);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        importancia = (Spinner) view.findViewById(R.id.spinner2);
        btncad = (Button) view.findViewById(R.id.buttonDespesa);
        impotantearray.add(String.valueOf(Importancia.URGENTE));
        impotantearray.add(String.valueOf(Importancia.RELEVANTE));
        impotantearray.add(String.valueOf(Importancia.TRANQUILO));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,impotantearray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importancia.setAdapter(adapter);


        data = Calendar.getInstance();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                data.set(year,month,dayOfMonth);
                Toast.makeText(getContext(),"ano:"+year+" mes "+month+" dia"+dayOfMonth+" ",Toast.LENGTH_SHORT).show();
            }
        });

        btncad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                despesa = new Despesa();
                despesa.setNomeDespesa(nomeDespesa.getText().toString());
                despesa.setValorDespesa(BigDecimal.valueOf(Double.parseDouble(valorDecimal.getText().toString())));
                importancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //despimportancia = impotantearray.get(position);
                        despimportancia = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getContext(),"item selecionado: " + despimportancia,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                despimportancia = importancia.getSelectedItem().toString();
                if(despimportancia == null || despimportancia.equals("")) {
                    despimportancia = "tranquilo";
                }
                System.out.println("status: "+despimportancia+"");
                imp = Importancia.valueOf(despimportancia);
                if(despimportancia != null) {
                    despesa.setImportancia(despimportancia);
                }
                    despesa.setDataVencimento(data);
                Usuario user = new Usuario();
                user.setId(2l);
                user.setNome("Juan");
                despesa.setUsuario(user);
                serviceD.salvar(getContext(),despesa);
            }
        });

    }
}
