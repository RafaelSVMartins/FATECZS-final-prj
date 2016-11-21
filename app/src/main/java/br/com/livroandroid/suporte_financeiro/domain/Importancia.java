package br.com.livroandroid.suporte_financeiro.domain;

/**
 * Created by Rrafael on 13/11/2016.
 */

public enum Importancia {
    URGENTE("urgente"),
    RELEVANTE("relevante"),
    TRANQUILO("tranquilo");
    private String descricao;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Importancia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }



}
