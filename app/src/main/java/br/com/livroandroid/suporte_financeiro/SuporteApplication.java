package br.com.livroandroid.suporte_financeiro;

import android.app.Application;
import android.util.Log;

import com.squareup.otto.Bus;

import br.com.livroandroid.suporte_financeiro.domain.Usuario;

/**
 * Created by Rrafael on 14/11/2016.
 */

public class SuporteApplication extends Application {
    private static final String TAG = "SuporteApplication";
    private static SuporteApplication instance= null;
    private Bus bus= new Bus();
    private Usuario userlogin = new Usuario();

    public static SuporteApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Suporte onCreate()");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG,"Suporte onTerminate()");
    }

    public Bus getBus() {
        return bus;
    }

    public Usuario getUserlogin() {
        return userlogin;
    }
}
