package br.com.livroandroid.suporte_financeiro.domain.Services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.Produto.BdCoreProduto;
import br.com.livroandroid.suporte_financeiro.Fragments.Renda.BdCoreRenda;
import br.com.livroandroid.suporte_financeiro.domain.Produto;
import br.com.livroandroid.suporte_financeiro.domain.Renda;

/**
 * Created by Rrafael on 08/12/2016.
 */

public class ProdutoService {

    private static final String TAG="RendaService";
    private static boolean LON_ON=false;

    public static List<Produto> getProdutos(Context context) throws IOException {
        List<Produto> produtos = getFromBanco(context);
        if (produtos != null &&  produtos.size() > 0) {
            Log.d(TAG,"Lista de rendas encontrada!");
            return produtos;
        } else {
            Log.d(TAG,"Rendas não encontradas!");
        }
        return produtos;
    }

    private static List<Produto> getFromBanco(Context context) {
        BdCoreProduto db = new BdCoreProduto(context);
        List<Produto> produtos = new ArrayList<>();
        try {
            produtos = db.findAll();
            Log.d(TAG,"Foi encontrada a lista de rendas");
        } catch (Exception e) {
            Log.d(TAG,e.getMessage());
        }
        return produtos;
    }

    public static void Delete(Context context,Produto produto) {
        BdCoreProduto db = new BdCoreProduto(context);
        try {
            db.delete(produto);
        } catch (Exception e) {
            Log.d(TAG,"Problemas com deleção: "+e.getMessage()+"");
        }
    }

    public static void Salvar(Context context, Produto produto) {
        BdCoreProduto db = new BdCoreProduto(context);
        try {
            db.save(produto);
            Log.d(TAG,"renda salva com sucesso!");
        } catch (Exception e) {
            Log.d(TAG,"Problemas com a operação salvar("+e.getMessage()+")!");
        }
    }
}
