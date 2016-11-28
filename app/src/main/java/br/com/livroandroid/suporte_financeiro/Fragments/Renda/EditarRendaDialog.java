package br.com.livroandroid.suporte_financeiro.Fragments.Renda;

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
import android.widget.EditText;

import java.math.BigDecimal;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 18/11/2016.
 */

public class EditarRendaDialog extends DialogFragment {
    private Callback callback;
    private EditText NomeRenda;
    private EditText TipoRenda;
    private EditText ValorRenda;
    private Button btnSalvar;
    private Renda renda;

    public interface Callback {
        void UpDateRenda(Renda renda);
    }

    public static void show(FragmentManager fm, Renda renda, Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("editar_renda");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditarRendaDialog editarRendaDialog = new EditarRendaDialog();
        editarRendaDialog.callback = callback;
        Bundle args = new Bundle();
        args.putParcelable("renda", renda);
        editarRendaDialog.setArguments(args);
        editarRendaDialog.show(ft, "editar_renda");
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
        View view = inflater.inflate(R.layout.fragment_cadastrar_renda,container,false);
        NomeRenda = (EditText) view.findViewById(R.id.nome_renda);
        TipoRenda = (EditText) view.findViewById(R.id.editTextRenda);
        ValorRenda = (EditText) view.findViewById(R.id.editTextValor);
        btnSalvar = (Button) view.findViewById(R.id.cadastrar_renda);
        this.renda = getArguments().getParcelable("renda");

        btnSalvar.setOnClickListener(onClickAtualizar());
        return view;
    }
    private View.OnClickListener onClickAtualizar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNome = NomeRenda.getText().toString();
                String novoTipo = TipoRenda.getText().toString();
                String novoValor = ValorRenda.getText().toString();

                if (novoNome == null || novoNome.trim().length() ==0) {
                    NomeRenda.setText("Nome Erro!");
                }

                if (novoTipo == null || novoTipo.trim().length() ==0) {
                    TipoRenda.setText("Tipo Erro!");
                }

                if (novoValor == null || novoValor.trim().length() ==0) {
                    ValorRenda.setText("Valor Erro!");
                }
                Context context = getView().getContext();
                Renda renda = new Renda();
                renda.setNomeRenda(novoNome);
                renda.setTipoRenda(novoTipo);
                renda.setValorRenda(BigDecimal.valueOf(Double.parseDouble(novoValor)));
                Usuario user = new Usuario();
                user.setId(2l);
                user.setNome("Juan");
                renda.setUsuario(user);
                if(callback != null) {
                    callback.UpDateRenda(renda);
                }
                dismiss();
            }
        };
    }
}
