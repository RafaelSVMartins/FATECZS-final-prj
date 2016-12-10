package br.com.livroandroid.suporte_financeiro.domain;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 07/12/2016.
 */

public class Relatorio {

    private BigDecimal valorTotallazer;
    private BigDecimal valorTotalImportante;
    private String Situacao;

    public String getTipoObjeto() {
        return TipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        TipoObjeto = tipoObjeto;
    }

    private String TipoObjeto;

    public BigDecimal getValorTotallazer() {
        return valorTotallazer;
    }

    public void setValorTotallazer(BigDecimal valorTotallazer) {
        this.valorTotallazer = valorTotallazer;
    }

    public BigDecimal getValorTotalImportante() {
        return valorTotalImportante;
    }

    public void setValorTotalImportante(BigDecimal valorTotalImportante) {
        this.valorTotalImportante = valorTotalImportante;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }
}
