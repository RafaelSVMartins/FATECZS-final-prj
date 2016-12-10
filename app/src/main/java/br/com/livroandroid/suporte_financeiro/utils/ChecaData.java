package br.com.livroandroid.suporte_financeiro.utils;

import java.util.Calendar;

/**
 * Created by Rrafael on 09/12/2016.
 */

public class ChecaData {
    private Calendar datateste;
    private Calendar data1;
    private int resposta;
    private int mes;
    public ChecaData(Calendar datateste, int mes) {
        this.datateste = datateste;
        this.mes = mes;
    }

    public int testeCalendar() {
        data1 = Calendar.getInstance();
        data1.add(Calendar.MONTH, mes);

        if(data1.after(datateste)) {
            return this.resposta = 1;
        } else if(data1.before(datateste)) {
            return this.resposta = -1;
        } else{
            return resposta=0;
        }

    }

}
