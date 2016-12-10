package br.com.livroandroid.suporte_financeiro.domain;

/**
 * Created by Rrafael on 13/11/2016.
 */

public enum Importancia {
    LAZER("Lazer"),
    ESSENCIAL("Esssencial");
    private String descricao;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    Importancia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }



}
