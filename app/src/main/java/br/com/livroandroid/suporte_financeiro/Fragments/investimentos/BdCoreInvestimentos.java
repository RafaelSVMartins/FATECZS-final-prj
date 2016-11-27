package br.com.livroandroid.suporte_financeiro.Fragments.investimentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;
import br.com.livroandroid.suporte_financeiro.domain.Investimento;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 13/11/2016.
 */

public class BdCoreInvestimentos  {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase db;
    private static final String TAG="SQL";

    public BdCoreInvestimentos(Context context) {
        bancoSuporte = new BancoSuporte(context);
        db = bancoSuporte.getWritableDatabase();
    }

    public long save(Investimento investimento) {
        long id = 0;
        if (investimento.getId() != null) {
            id = investimento.getId();
        }
        try {
            ContentValues values = new ContentValues();
            values.put("nome",investimento.getNomeInvestimento());
            values.put("valor", investimento.getValorInvestimento().doubleValue());
            values.put("tipo", investimento.getTipoInvestimento());
            values.put("importancia", investimento.getImportancia().getDescricao());
            values.put("data", investimento.getVencimentoInvestimento().getTimeInMillis());
            values.put("usuario_idUsuario", investimento.getUsuario().getId());
            if (id != 0) {
                String _id = String.valueOf(investimento.getId());
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

    public int delete(Investimento investimento) {
        try {
            int count = db.delete("investimento","_idInvestimento=?", new String[]{String.valueOf(investimento.getId())});
            Log.i(TAG,"Deletou ["+count+"] registro");
            return count;
        } finally {
            db.close();
        }
    }

    public List<Investimento> findAll() {
        try {
            Cursor c = db.query("investimento",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    private List<Investimento> toList(Cursor c) {
        List<Investimento> investimentos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        java.util.Date dt=null;
        long datamili;
        Calendar t = new GregorianCalendar();
        if (c.moveToFirst()) {
            do {
                Investimento investimento = new Investimento();
                investimento.setId(c.getLong(c.getColumnIndex("_idInvestimento")));
                investimento.setNomeInvestimento(c.getString(c.getColumnIndex("nome")));
                investimento.setTipoInvestimento(c.getString(c.getColumnIndex("tipo")));
                investimento.setImportancia(Importancia.valueOf(c.getString(c.getColumnIndex("importancia"))));
                investimento.setValorInvestimento(BigDecimal.valueOf(c.getDouble(c.getColumnIndex("valor"))));
                datamili = (c.getLong(c.getColumnIndex("vencimento")));
                dt.setTime(datamili);
                t.setTime(dt);
                investimento.setVencimentoInvestimento(t);
                investimento.getUsuario().setId(c.getLong(c.getColumnIndex("usuario_idUsuario")));
                investimentos.add(investimento);
            } while(c.moveToNext());
        }
        return investimentos;
    }

}
