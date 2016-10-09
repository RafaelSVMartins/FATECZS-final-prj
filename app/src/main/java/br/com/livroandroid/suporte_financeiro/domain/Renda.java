package br.com.livroandroid.suporte_financeiro.domain;

import org.parceler.Parcel;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 09/10/2016.
 */

@Parcel
public class Renda {

    private String nomeRenda;
    private BigDecimal valorRenda;

    public Renda() {

    }

    public Renda(String nomeRenda, BigDecimal valorRenda) {
        this.nomeRenda = nomeRenda;
        this.valorRenda = valorRenda;
    }

    public String getNomeRenda() {
        return nomeRenda;
    }

    public void setNomeRenda(String nomeRenda) {
        this.nomeRenda = nomeRenda;
    }

    public BigDecimal getValorRenda() {
        return valorRenda;
    }

    public void setValorRenda(BigDecimal valorRenda) {
        this.valorRenda = valorRenda;
    }

    @Override
    public String toString() {
        return "Renda{" +
                "nomeRenda='" + nomeRenda + '\'' +
                ", valorRenda=" + valorRenda +
                '}';
    }

}
