package br.com.livroandroid.suporte_financeiro.Activity.ListaActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.livroandroid.suporte_financeiro.Activity.DespesaActivity.DespesaActivityFragment;
import br.com.livroandroid.suporte_financeiro.R;

public class ListaOperacoes extends AppCompatActivity {

    private Button cadastrarItem;
    private Button visualizarItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_operacoes);
        cadastrarItem = (Button) findViewById(R.id.button9);
        visualizarItem = (Button) findViewById(R.id.button10);
        cadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CadastrarLista.class);
                startActivity(intent);
            }
        });
        visualizarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VisualizarLista.class);
                startActivity(intent);
            }
        });
    }
}
