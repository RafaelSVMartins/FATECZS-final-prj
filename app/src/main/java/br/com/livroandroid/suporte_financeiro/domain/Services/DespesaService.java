package br.com.livroandroid.suporte_financeiro.domain.Services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.despesa.BdCoreDespesas;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;

/**
 * Created by Rrafael on 20/11/2016.
 */

public class DespesaService {
    private static final String TAG="DespesaSuporte";
    private static boolean LONG_ON=false;

    public static List<Despesa> getDespesas(Context context) throws IOException {
        List<Despesa> despesas = getFromBanco(context);
        if (despesas != null && despesas.size() > 0) {
            Log.i(TAG,"Lista de despesas encontradas!");
            return despesas;
        } else {
            Log.i(TAG,"Lista de despesas não foi encontrada!");
        }
        return despesas;
    }

    private static List<Despesa> getFromBanco(Context context) {
        BdCoreDespesas db = new BdCoreDespesas(context);
        List<Despesa> despesas = null;
        try {
            despesas = db.findAll();
            Log.i(TAG,"Lista de despesas encontradas!");
            return despesas;
        } catch (Exception e) {
            Log.i(TAG,"Lista de despesas não encontradas( "+ e.getMessage() +")!");
        }
        return despesas;
    }

    public static void salvar(Context context,Despesa despesa) {
        BdCoreDespesas db = new BdCoreDespesas(context);
        try {
            db.save(despesa);
            Log.i(TAG,"Despesa salva com sucesso!");
        } catch (Exception e) {
            Log.i(TAG,"Despesa não foi salva com sucesso!");
        }
    }

    public static void deletar(Context context,Despesa despesa) {
        BdCoreDespesas db = new BdCoreDespesas(context);
        try {
            db.Delete(despesa);
            Log.i(TAG,"Despesa deletada com sucesso!");
        } catch (Exception e) {
            Log.i(TAG,"Despesa não foi deletada!");
        }
    }
}
