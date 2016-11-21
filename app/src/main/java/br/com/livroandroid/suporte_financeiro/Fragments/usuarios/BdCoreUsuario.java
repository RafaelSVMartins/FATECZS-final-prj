package br.com.livroandroid.suporte_financeiro.Fragments.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 06/11/2016.
 */

public class BdCoreUsuario {
    private BancoSuporte bancoSuporte;
    private SQLiteDatabase db;
    private static final String TAG="sql";
    public BdCoreUsuario(Context context) {
        bancoSuporte = new BancoSuporte(context);
        db = bancoSuporte.getWritableDatabase();
    }

    public long save(Usuario usuario) {
        long id=0;
        if (usuario.getId() != null) {
             id = usuario.getId();
        }
        try {
            ContentValues values = new ContentValues();
            values.put("nome", usuario.getNome());
            values.put("sexo", usuario.getSexo());
            values.put("email", usuario.getEmail());
            if (id != 0) {
                String _id = String.valueOf(usuario.getId());
                long count = db.update("usuario",values,"_idUsuario = " + _id,null);
                Log.d(TAG,"atualizado com sucesso!");
                return count;
            } else {
                id = db.insert("usuario","",values);
                Log.d(TAG,"inserido com sucesso!");
                return id;
            }
        } finally {
            db.close();
        }
    }

    public int delete(Usuario usuario) {
        try {
            int count = db.delete("usuario","_idUsuario=?", new String[]{String.valueOf(usuario.getId())});
            Log.i(TAG,"Deletou ["+count+"] registro");
            return count;
        } finally {
            db.close();
        }
    }

    public List<Usuario> findAll() {
        try {
            Cursor c = db.query("usuario",null,null,null,null,null,null,null);
            return toList(c);
        } finally {
            db.close();
        }
    }



    private List<Usuario> toList(Cursor c) {
        List<Usuario> usuarios = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(c.getLong(c.getColumnIndex("_idUsuario")));
                u.setNome(c.getString(c.getColumnIndex("nome")));
                u.setSexo(c.getString(c.getColumnIndex("sexo")));
                usuarios.add(u);
            } while(c.moveToNext());
        }
        return usuarios;
    }

    public Usuario FindAllByNome(Usuario usuario) {
        Usuario u = null;
        try {
            //select * from carro where tipo = ?
            Cursor c = db.query("usuario", new String[] {"_idUsuario","nome","sexo","email"}, "nome=?", new String[]{usuario.getNome()}, null, null, null);
            if (c.moveToFirst()) {
                u = new Usuario();
                u.setId(c.getLong(c.getColumnIndex("_idUsuario")));
                u.setNome(c.getString(c.getColumnIndex("nome")));
                u.setSexo(c.getString(c.getColumnIndex("sexo")));
                u.setEmail(c.getString(c.getColumnIndex("email")));
                return u;
            }
        } finally {
            db.close();
        }
        return u;
    }
}
