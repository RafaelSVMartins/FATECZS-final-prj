package br.com.livroandroid.suporte_financeiro.Fragments.Renda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 13/11/2016.
 */

public class BdCoreRenda  {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase db;
    private static final String TAG="sql";

    public BdCoreRenda(Context context) {
        bancoSuporte = new BancoSuporte(context);
        db = bancoSuporte.getWritableDatabase();
    }

    public long save(Usuario usuario, Renda renda) {
        long id = 0;
        if (renda.getId() != null) {
            id = renda.getId();
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

    }

    public int delete(Usuario usuario, Renda renda) {
        try {
            int count = db.delete("renda","_idRenda=?", new String[]{String.valueOf(renda.getId())});
            Log.i(TAG,"Deletou ["+count+"] registro");
            return count;
        } finally {
            db.close();
        }
    }

    public List<Renda> findAll() {
        try {
            Cursor c = db.query("renda",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    private List<Renda> toList(Cursor c) {
        List<Renda> rendas = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Renda renda = new Renda();
                renda.setId(c.getLong(c.getColumnIndex("_idRenda")));
                renda.setNomeRenda(c.getString(c.getColumnIndex("nome")));
                renda.setTipoRenda(c.getString(c.getColumnIndex("tipo")));
                renda.setValorRenda(BigDecimal.valueOf(c.getDouble(c.getColumnIndex("valor"))));
                renda.setUsuarioId(c.getLong(c.getColumnIndex("_idRenda")));
                rendas.add(renda);
            } while(c.moveToNext());
        }
        return rendas;
    }

}
