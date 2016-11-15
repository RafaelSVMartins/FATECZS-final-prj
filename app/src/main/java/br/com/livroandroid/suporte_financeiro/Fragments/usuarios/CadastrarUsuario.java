package br.com.livroandroid.suporte_financeiro.Fragments.usuarios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Services.UsuarioService;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarUsuario extends Fragment {
    private EditText nomeUsuario;
    private Spinner sexo;
    private Usuario usu;
    private Button btncadastrar;
    private BdCoreUsuario bdCoreUsuario;
    private String[] nomesexos;
    private String nome;
    private String nsexo;
    public CadastrarUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_usuario, container, false);
        nomesexos = new String[] {"masculino","feminino"};
        bdCoreUsuario =  new BdCoreUsuario(getContext());
        sexo = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,nomesexos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sexo.setAdapter(arrayAdapter);
        nomeUsuario = (EditText) view.findViewById(R.id.cadu_nome);
        btncadastrar = (Button) view.findViewById(R.id.button3);
        usu = new Usuario();
        /*usu.setNome(String.valueOf(nomeUsuario.getText()));
        usu.setSexo(String.valueOf(sexo.getSelectedItem()));*/

        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = nomeUsuario.getText().toString();
                nsexo = sexo.getSelectedItem().toString();
                usu.setNome(nome);
                usu.setSexo(nsexo);
                if (usu.getNome() != null && usu.getSexo() != null) {
                    UsuarioService.SalvarUsuarios(getContext(),usu);
                }
            }
        });

        return view;

    }

}
