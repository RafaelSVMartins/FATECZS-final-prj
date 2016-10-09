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
}
