package br.com.livroandroid.suporte_financeiro.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rrafael on 06/11/2016.
 */

public class BancoSuporte extends SQLiteOpenHelper {
    private static final String TAG="sql";
    private static final String NOME_BANCO="bdSuporte";
    private static final int VERSAO_BANCO=1;

    public BancoSuporte(Context context) {
        super(context, "bdSuporte", null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabelas");
        db.execSQL("CREATE TABLE if not exists usuario (_idUsuario integer primary key autoincrement, nome text, sexo text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario");
        onCreate(db);
    }
}
