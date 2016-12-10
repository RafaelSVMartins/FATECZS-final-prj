package br.com.livroandroid.suporte_financeiro.Activity.ListaActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import br.com.livroandroid.suporte_financeiro.Fragments.Produto.BdCoreProduto;
import br.com.livroandroid.suporte_financeiro.R;
import br.com.livroandroid.suporte_financeiro.domain.Produto;
import br.com.livroandroid.suporte_financeiro.domain.Services.ProdutoService;

public class CadastrarLista extends AppCompatActivity {
    private Button btncad;
    private static final String TAG ="PRODUTO";
    private EditText nomeProduto;
    private EditText QuantidadeProduto;
    private Produto produto;
    private ProdutoService ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lista);
        nomeProduto = (EditText) findViewById(R.id.editNomeProduto);
        QuantidadeProduto = (EditText) findViewById(R.id.editQuantidadeProduto);
        btncad = (Button) findViewById(R.id.btnCadProduto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btncad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto = new Produto();
                produto.setNomeProduto(nomeProduto.getText().toString());
                produto.setQuantidadeProduto(Long.parseLong(QuantidadeProduto.getText().toString()));
                String FILENAME = "Listaprodutos";
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
                    OutputStreamWriter f= new OutputStreamWriter(fos);
                    f.write(produto.toString());
                    f.close();
                    fos.close();
                    ps.Salvar(getBaseContext(),produto);
                } catch (java.io.IOException e) {
                    Log.d(TAG,"Problemas com arquivos "+e.getMessage()+".");
                }
            }
        });

    }
}
