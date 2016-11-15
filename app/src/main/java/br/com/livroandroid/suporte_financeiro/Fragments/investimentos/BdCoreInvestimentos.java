package br.com.livroandroid.suporte_financeiro.Fragments.investimentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 13/11/2016.
 */

public class BdCoreInvestimentos  {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase db;

    public BdCoreInvestimentos(Context context) {
        bancoSuporte = new BancoSuporte(context);
        db = bancoSuporte.getWritableDatabase();
    }

   /* public long save(Usuario usuario, Investimento investimento) {
        long id = 0;
        if (usuario.getId() != null) {
            id = usuario.getId();
        }
        try {
            ContentValues values = new ContentValues();
            values.put("nome", renda.getNomeRenda());
            values.put("tipo", renda.getTipoRenda());
            values.put("valor", renda.getValorRenda().doubleValue());
            values.put("usuario_idUsuario", usuario.getId());
            if (id != 0) {
                String _id = String.valueOf(renda.getId());
                long count = db.update("renda",values,"_idRenda = " + _id,null);
                return count;
            } else {
                id = db.insert("renda","",values);
                return id;
            }
        } finally {
            db.close();
        }

    }*/
}
