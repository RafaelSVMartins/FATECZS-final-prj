package br.com.livroandroid.suporte_financeiro.domain.Services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.Renda.BdCoreRenda;
import br.com.livroandroid.suporte_financeiro.domain.Renda;

/**
 * Created by Rrafael on 17/11/2016.
 */

public class RendaService {

    private static final String TAG="RendaService";
    private static boolean LON_ON=false;

    public static List<Renda> getRendas(Context context) throws IOException {
        List<Renda> rendas = getFromBanco(context);
        if (rendas != null && rendas.size() > 0) {
            Log.d(TAG,"Lista de rendas encontrada!");
            return rendas;
        } else {
            Log.d(TAG,"Rendas não encontradas!");
        }
        return rendas;
    }

    private static List<Renda> getFromBanco(Context context) {
        BdCoreRenda db = new BdCoreRenda(context);
        List<Renda> rendas = new ArrayList<>();
        try {
            rendas = db.findAll();
            Log.d(TAG,"Foi encontrada a lista de rendas");
        } catch (Exception e) {
            Log.d(TAG,e.getMessage());
        }
        return rendas;
    }

    public static void Delete(Context context,Renda renda) {
        BdCoreRenda db = new BdCoreRenda(context);
        try {
            db.delete(renda);
        } catch (Exception e) {
            Log.d(TAG,"Problemas com deleção: "+e.getMessage()+"");
        }
    }

    public static void Salvar(Context context, Renda renda) {
        BdCoreRenda db = new BdCoreRenda(context);
        try {
            db.save(renda);
            Log.d(TAG,"renda salva com sucesso!");
        } catch (Exception e) {
            Log.d(TAG,"Problemas com a operação salvar("+e.getMessage()+")!");
        }
    }
}
