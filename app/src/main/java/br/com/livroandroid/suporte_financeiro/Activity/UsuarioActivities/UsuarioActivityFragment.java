package br.com.livroandroid.suporte_financeiro.Activity.UsuarioActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.livroandroid.suporte_financeiro.Activity.Base_Activity;
import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.TabsUsuariosFragment;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

public class UsuarioActivityFragment extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_fragment);
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Usuários");
        if (savedInstanceState == null) {
            TabsUsuariosFragment tabfrag = new TabsUsuariosFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_usuario_activity_fragment,tabfrag).commit();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
