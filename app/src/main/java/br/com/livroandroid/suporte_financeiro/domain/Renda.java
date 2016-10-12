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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Renda renda = (Renda) o;

        if (!nomeRenda.equals(renda.nomeRenda)) return false;
        return valorRenda.equals(renda.valorRenda);

    }

    @Override
    public int hashCode() {
        int result = nomeRenda.hashCode();
        result = 31 * result + valorRenda.hashCode();
        return result;
    }
}
