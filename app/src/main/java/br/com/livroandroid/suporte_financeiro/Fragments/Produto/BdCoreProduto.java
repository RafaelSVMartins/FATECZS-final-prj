package br.com.livroandroid.suporte_financeiro.Fragments.Produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Produto;
import br.com.livroandroid.suporte_financeiro.domain.Renda;

/**
 * Created by Rrafael on 08/12/2016.
 */

public class BdCoreProduto {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase db;
    private static final String TAG="sql";

    public BdCoreProduto(Context context) {

        bancoSuporte = new BancoSuporte(context);
        db = bancoSuporte.getWritableDatabase();
    }

    public long save(Produto produto) {
        long id = 0;
        if (produto.getId() != null) {
            id = produto.getId();
        }
        try {
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNomeProduto());
            values.put("quantidade", produto.getQuantidadeProduto());
            if (id != 0) {
                String _id = String.valueOf(produto.getId());
                long count = db.update("produto",values,"_idProduto = " + _id,null);
                return count;
            } else {
                id = db.insert("produto","",values);
                return id;
            }
        } finally {
            db.close();
        }

    }

    public int delete(Produto produto) {
        try {
            int count = db.delete("produto","_idProduto=?", new String[]{String.valueOf(produto.getId())});
            Log.i(TAG,"Deletou ["+count+"] registro");
            return count;
        } finally {
            db.close();
        }
    }


    private List<Produto> toList(Cursor c) {
        List<Produto> produtos = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex("_idProduto")));
                produto.setNomeProduto(c.getString(c.getColumnIndex("nome")));
                produto.setQuantidadeProduto(c.getLong(c.getColumnIndex("quantidade")));
                produtos.add(produto);
            } while(c.moveToNext());
        }
        return produtos;
    }

    public List<Produto> findAll() {
        try {
            Cursor c = db.query("produto",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }
}
