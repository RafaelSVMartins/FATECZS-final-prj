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
    private static final int VERSAO_BANCO=2;

    public BancoSuporte(Context context) {
        super(context, "bdSuporte", null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabelas");
        db.execSQL("CREATE TABLE if not exists usuario (_idUsuario integer primary key autoincrement, nome text, sexo text);");
        db.execSQL("CREATE TABLE if not exists renda (_idRenda integer primary key autoincrement,nome text not null, tipo text not null, valor real not null,  usuario_idUsuario integer, foreign key(usuario_idUsuario) references usuario(_idUsuario));");
        db.execSQL("CREATE TABLE if not exists despesa (_idDespesa integer primary autoincrement, nome text not null, tipo text not null, valor real not null, dataVencimento date not null, usuario_idUsuario integer, foreign key(usuario_idUsuario) references usuario(_idUsuario));");
        db.execSQL("CREATE TABLE if not exists investimento (_idInvestimento integer primary autoincrement, nome text not null, tipo text not null, importancia text not null, valor real not null, vencimento Date not null, usuario_idUsuario integer, foreign key(usuario_idUsuario) references usuario(_idUsuario));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario");
        db.execSQL("drop table renda");
        db.execSQL("drop table investimento");
        db.execSQL("drop table despesa");
        onCreate(db);
    }
}
