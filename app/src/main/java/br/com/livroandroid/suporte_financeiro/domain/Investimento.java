package br.com.livroandroid.suporte_financeiro.domain;

import org.parceler.Parcel;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 09/10/2016.
 */
@Parcel
public class Investimento {

    private String nomeInvestimento;
    private BigDecimal valorInvestimento;

    public Investimento(String nomeInvestimento, BigDecimal valorInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
        this.valorInvestimento = valorInvestimento;
    }

    public Investimento() {

    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public BigDecimal getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(BigDecimal valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    @Override
    public String toString() {
        return "Investimento{" +
                "nomeInvestimento='" + nomeInvestimento + '\'' +
                ", valorInvestimento=" + valorInvestimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investimento that = (Investimento) o;

        if (!nomeInvestimento.equals(that.nomeInvestimento)) return false;
        return valorInvestimento.equals(that.valorInvestimento);

    }

    @Override
    public int hashCode() {
        int result = nomeInvestimento.hashCode();
        result = 31 * result + valorInvestimento.hashCode();
        return result;
    }
}
