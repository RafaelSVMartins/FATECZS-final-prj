package br.com.livroandroid.suporte_financeiro.Fragments.Renda;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.math.BigDecimal;

import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.BdCoreUsuario;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Services.RendaService;
import br.com.livroandroid.suporte_financeiro.domain.Services.UsuarioService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarRenda extends Fragment {

    private Context context;
    public Usuario user=null;
    private EditText NomeRenda;
    private EditText TipoRenda;
    private EditText ValorRenda;
    private Button btnSalvar;
    private Renda renda;
    public CadastrarRenda() {
        // Required empty public constructor
        context = getContext();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_renda, container, false);
        NomeRenda = (EditText) view.findViewById(R.id.nome_renda);
        TipoRenda = (EditText) view.findViewById(R.id.editTextRenda);
        ValorRenda = (EditText) view.findViewById(R.id.editTextValor);
        btnSalvar = (Button) view.findViewById(R.id.cadastrar_renda);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renda = new Renda();
                renda.setNomeRenda(NomeRenda.getText().toString());
                renda.setTipoRenda(TipoRenda.getText().toString());
                renda.setValorRenda(BigDecimal.valueOf(Double.parseDouble(ValorRenda.getText().toString())));
                user = new Usuario();
                user.setId(2l);
                user.setNome("Juan");
                renda.setUsuario(user);

                RendaService.Salvar(getContext(),renda);
            }
        });
        return view;
    }

}
