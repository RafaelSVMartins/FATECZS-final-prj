package br.com.livroandroid.suporte_financeiro.domain.Services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.investimentos.BdCoreInvestimentos;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;

/**
 * Created by Rrafael on 20/11/2016.
 */

public class InvestimentoService {
    private static final String TAG="InvestimentoService";
    private static boolean LONG_ON=false;

    public static List<Investimento> getInvestimentos(Context context) throws IOException {
        List<Investimento> investimentos = getFromBanco(context);
        if(investimentos != null && investimentos.size() > 0) {
            Log.i(TAG,"Lista de investimentos encontrada!");
            return investimentos;
        } else {
            Log.i(TAG,"Lista de investimentos não foi encontrada!");
        }
        return investimentos;
    }

    private static List<Investimento> getFromBanco(Context context) {
        BdCoreInvestimentos db = new BdCoreInvestimentos(context);
        List<Investimento> investimentos = null;
        try {
            investimentos = db.findAll();
            Log.i(TAG,"Lista de investimentos encontrada!");
            return investimentos;
        } catch (Exception e) {
            Log.i(TAG,"Lista de investimentos não foi encontrada( "+ e.getMessage() +")!");
        }
        return investimentos;
    }

    public static void Salvar(Context context,Investimento investimento) {
        BdCoreInvestimentos db = new BdCoreInvestimentos(context);
        try {
            db.save(investimento);
            Log.i(TAG,"Investimento salvo com sucesso!");
        } catch (Exception e) {
            Log.i(TAG,"Investimento não pode ser salvo!");
        }
    }

    public static void Deletar(Context context,Investimento investimento) {
        BdCoreInvestimentos db = new BdCoreInvestimentos(context);
        try {
            db.delete(investimento);
            Log.i(TAG,"Investimento deletado com sucesso!");
        } catch (Exception e) {
            Log.i(TAG,"Problemas na deleção!");
        }
    }
}
