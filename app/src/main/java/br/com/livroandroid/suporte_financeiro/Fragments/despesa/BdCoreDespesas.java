package br.com.livroandroid.suporte_financeiro.Fragments.despesa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Despesa;
import br.com.livroandroid.suporte_financeiro.domain.Importancia;
import br.com.livroandroid.suporte_financeiro.domain.Renda;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 13/11/2016.
 */

public class BdCoreDespesas {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase dbD;
    private static final String TAG="DespesasSuporte";

    public BdCoreDespesas(Context context) {
        bancoSuporte = new BancoSuporte(context);
        dbD = bancoSuporte.getWritableDatabase();
    }

    public long save(Despesa despesa) {
        long id=0;
        if (despesa.getId() != null) {
            id = despesa.getId();
        }
        try {
            ContentValues values = new ContentValues();
            values.put("nome",despesa.getNomeDespesa());
            values.put("valor",despesa.getValorDespesa().doubleValue());
            values.put("dataVencimento",despesa.getDataVencimento().getTimeInMillis());
            values.put("importancia",despesa.getImportancia());
            values.put("usuario_idUsuario",despesa.getUsuario().getId());
            if (id != 0) {
                String _id = String.valueOf(despesa.getId());
                long count = dbD.update("despesa",values,"_idDespesa = " + _id, null);
                Log.d(TAG,"atualizado com sucesso!");
                return count;
            } else {
                id = dbD.insert("despesa","",values);
                Log.d(TAG,"inserido com sucesso!");
                return id;
            }
        } finally {
            dbD.close();
        }
    }

    public int Delete(Despesa despesa) {
        try {
            int id = dbD.delete("despesa","_idDespesa = ",new String[]{String.valueOf(despesa.getId())});
            Log.i(TAG,"Deletou ["+id+"] registro");
            return id;
        } finally {
            dbD.close();
        }
    }

    public List<Despesa> findAll() {
        try {
            Cursor c = dbD.query("despesa",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            dbD.close();
        }
    }

    private List<Despesa> toList(Cursor c) {
        List<Despesa> depesas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long datamili;
        Calendar t = Calendar.getInstance();
        if (c.moveToFirst())
            do {
                Date dt = null;
                Despesa d = new Despesa();
                String formatadata = new String();
                d.setId(c.getLong(c.getColumnIndex("_idDespesa")));
                d.setNomeDespesa(c.getString(c.getColumnIndex("nome")));
                d.setValorDespesa(BigDecimal.valueOf(c.getDouble(c.getColumnIndex("valor"))));
                datamili = (c.getLong(c.getColumnIndex("dataVencimento")));
                formatadata = String.valueOf(datamili);
                try {
                    dt = (Date) sdf.parse(formatadata);
                } catch (Exception e) {
                   throw new RuntimeException("erro na conversão de data: "+e.getMessage()+"");
                }
                t.setTime(dt);
                d.setDataVencimento(t);
                d.setImportancia(c.getString(c.getColumnIndex("importancia")));
                //d.getUsuario().setId(c.getLong(c.getColumnIndex("usuario_idUsuario")));
                depesas.add(d);
            } while (c.moveToNext());
        return depesas;
    }
}
