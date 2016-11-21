package br.com.livroandroid.suporte_financeiro.Fragments.usuarios;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 15/11/2016.
 */

public class EditarUsuarioDialog extends DialogFragment {
    private Callback callback;
    private Usuario usuario;
    private EditText nomeUsuario;
    private Spinner sexousuario;
    private EditText emailusuario;
    private String[] nomesexos;
    private Button btnCadastrar;

    public interface Callback {
        void OnUpdateUsuario(Usuario usuario);
    }

    public static void show(FragmentManager fm, Usuario usuario, Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev= fm.findFragmentByTag("cadastrar_usuario");

        if(prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditarUsuarioDialog frag = new EditarUsuarioDialog();
        frag.callback = callback;
        Bundle args = new Bundle();
        args.putParcelable("usuario",usuario);
        frag.setArguments(args);
        frag.show(ft, "editar_usuario");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null) {
            return;
        }
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width,height);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_usuario,container,false);
        nomesexos = new String[] {"masculino","feminino"};
        sexousuario = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,nomesexos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sexousuario.setAdapter(arrayAdapter);
        emailusuario = (EditText) view.findViewById(R.id.editTextEmail);
        nomeUsuario = (EditText) view.findViewById(R.id.cadu_nome);
        btnCadastrar = (Button)  view.findViewById(R.id.button3);
        btnCadastrar.setOnClickListener(OnClickUpdate());
        this.usuario = getArguments().getParcelable("usuario");
        if (usuario != null) {
            nomeUsuario.setText(usuario.getNome());
            emailusuario.setText(usuario.getEmail());
            sexousuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    usuario.getSexo();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        return view;
    }

    private View.OnClickListener OnClickUpdate() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoNome = nomeUsuario.getText().toString();
                String novoEmail = emailusuario.getText().toString();
                String novoSexo = sexousuario.getSelectedItem().toString();
                if (novoNome == null || novoNome.trim().length() == 0) {
                    nomeUsuario.setText("Erro no nome");
                }
                if (novoEmail == null || novoEmail.trim().length() == 0) {
                    emailusuario.setText("erro no email");
                }
                Context context = getView().getContext();
                usuario.setNome(novoNome);
                usuario.setEmail(novoEmail);
                usuario.setSexo(novoSexo);
                if (callback != null) {
                    callback.OnUpdateUsuario(usuario);
                }
                dismiss();
            }
        };
    }
}
