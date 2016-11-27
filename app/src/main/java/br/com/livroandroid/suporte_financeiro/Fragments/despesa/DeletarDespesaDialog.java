package br.com.livroandroid.suporte_financeiro.Fragments.despesa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

/**
 * Created by Rrafael on 21/11/2016.
 */

public class DeletarDespesaDialog extends DialogFragment {
    private Callback callback;
    public interface Callback {
        void onClickYes();
    }

    public static void show(FragmentManager fm, Callback callback) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("deletar_despesa");

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DeletarDespesaDialog desp = new DeletarDespesaDialog();
        desp.callback = callback;
        desp.show(ft,"deletar_despesa");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if(callback != null){
                            //Confirmou que vai deletar o carro
                            callback.onClickYes();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deletar essa despesa?");
        builder.setPositiveButton("Sim", dialogClickListener);
        builder.setNegativeButton("Não", dialogClickListener);
        return builder.create();
    }
}
