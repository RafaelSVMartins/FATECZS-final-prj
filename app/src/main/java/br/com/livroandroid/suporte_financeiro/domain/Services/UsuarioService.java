package br.com.livroandroid.suporte_financeiro.domain.Services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.suporte_financeiro.Fragments.usuarios.BdCoreUsuario;
import br.com.livroandroid.suporte_financeiro.domain.BancoSuporte;
import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 07/11/2016.
 */

public class UsuarioService  {
    private static final String TAG="UsuarioService";
    private static boolean LON_ON=false;

    public static List<Usuario> getUsuarios(Context context) throws IOException {
        List<Usuario> usuarios = getFromBanco(context);
        if(usuarios != null && usuarios.size() > 0) {
            return usuarios;
        } else {
            Log.d(TAG,"Os usuários não foram encontrados no banco!");
        }
        return usuarios;
    }

    private static List<Usuario> getFromBanco(Context context) {
        BdCoreUsuario bd = new BdCoreUsuario(context);
        List<Usuario> usuarios=null;
        try {
            usuarios = bd.findAll();
            Log.d(TAG,"Foi encontrada a lista de usuarios");
        } catch (Exception e) {
            Log.d(TAG,"Não foi achada a lista de usuarios");
        }
        return usuarios;
    }

    public static void SalvarUsuarios(Context context,Usuario usuario) {
        BdCoreUsuario bd = new BdCoreUsuario(context);
        try {
            bd.save(usuario);
            Log.d(TAG,"Usuario salvo com sucesso!");
        } catch (Exception e) {
            Log.d(TAG,"Não foi possível salvar o usuario");
        }
    }

    public static void DeletaUsuario(Context context, Usuario usuario) {
        BdCoreUsuario bd = new BdCoreUsuario(context);
        try {
            bd.delete(usuario);
        } catch (Exception e) {
            Log.i(TAG,"Problemas com deleção");
        }

    }

}
