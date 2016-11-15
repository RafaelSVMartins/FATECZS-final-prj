package br.com.livroandroid.suporte_financeiro.Fragments.Renda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.livroandroid.suporte_financeiro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarRenda extends Fragment {

    private EditText NomeRenda;
    private EditText TipoRenda;
    private EditText ValorRenda;
    public CadastrarRenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_renda, container, false);
        return view;
    }

}
