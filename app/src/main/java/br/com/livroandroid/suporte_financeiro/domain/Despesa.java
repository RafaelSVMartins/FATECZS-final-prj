package br.com.livroandroid.suporte_financeiro.domain;

import org.parceler.Parcel;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Rrafael on 09/10/2016.
 */

@Parcel
public class Despesa {

    private String nomeDespesa;
    private BigDecimal valorDespesa;
    private Calendar DataVencimento;

    public Despesa() {

    }

    public Despesa(String nomeDespesa, BigDecimal valorDespesa, Calendar dataVencimento) {
        this.nomeDespesa = nomeDespesa;
        this.valorDespesa = valorDespesa;
        DataVencimento = dataVencimento;
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Calendar getDataVencimento() {
        return DataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        DataVencimento = dataVencimento;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "nomeDespesa='" + nomeDespesa + '\'' +
                ", valorDespesa=" + valorDespesa +
                ", DataVencimento=" + DataVencimento +
                '}';
    }
}
