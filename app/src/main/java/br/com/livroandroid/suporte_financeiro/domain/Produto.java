package br.com.livroandroid.suporte_financeiro.domain;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 03/12/2016.
 */

public class Produto {
    private Long id;
    private String NomeProduto;
    private String TipoProduto;
    private BigDecimal ValorProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public String getTipoProduto() {
        return TipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        TipoProduto = tipoProduto;
    }

    public BigDecimal getValorProduto() {
        return ValorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        ValorProduto = valorProduto;
    }
}
