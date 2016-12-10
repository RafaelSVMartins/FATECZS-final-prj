package br.com.livroandroid.suporte_financeiro.Fragments.Produto;

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

import br.com.livroandroid.suporte_financeiro.Fragments.Renda.EditarRendaDialog;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Produto;
import br.com.livroandroid.suporte_financeiro.domain.Renda;

/**
 * Created by Rrafael on 08/12/2016.
 */

public class EditarProdutoDialog extends DialogFragment {
    private EditarProdutoDialog.Callback callback;
    private EditText NomeProduto;
    private EditText QuantidadeProduto;
    private Button btnSalvar;
    private Produto produto;

    public interface Callback {
        void UpDateProduto(Produto produto);
    }

    public static void show(FragmentManager fm, Produto produto, EditarProdutoDialog.Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("editar_produto");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditarProdutoDialog editarRendaDialog = new EditarProdutoDialog();
        editarRendaDialog.callback = callback;
        Bundle args = new Bundle();
        args.putParcelable("produto", produto);
        editarRendaDialog.setArguments(args);
        editarRendaDialog.show(ft, "editar_produto");
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
        View view = inflater.inflate(R.layout.activity_cadastrar_lista,container,false);
        NomeProduto = (EditText) view.findViewById(R.id.editNomeProduto);
        QuantidadeProduto = (EditText) view.findViewById(R.id.editQuantidadeProduto);
        btnSalvar = (Button) view.findViewById(R.id.btnCadProduto);
        this.produto = getArguments().getParcelable("produto");
        btnSalvar.setOnClickListener(onClickAtualizar());
        return view;
    }

    private View.OnClickListener onClickAtualizar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNome = NomeProduto.getText().toString();
                String novaQuantidade = QuantidadeProduto.getText().toString();

                if (novoNome != null ||novoNome.equals("") || novoNome.trim().length() == 0) {
                    NomeProduto.setText("Erro no nome!");
                }

                if (novaQuantidade != null ||novaQuantidade.equals("") || novaQuantidade.trim().length() == 0) {
                    QuantidadeProduto.setText("Erro no nome!");
                }

                Context context = getView().getContext();
                Produto p = new Produto();
                p.setNomeProduto(novoNome);
                p.setQuantidadeProduto(Long.parseLong(novaQuantidade.toString()));
                if(callback != null) {
                    callback.UpDateProduto(p);
                }
                dismiss();
            }
        };
    }
}
