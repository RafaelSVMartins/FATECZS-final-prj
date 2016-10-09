package br.com.livroandroid.suporte_financeiro.domain;

import org.parceler.Parcel;

/**
 * Created by Rrafael on 09/10/2016.
 */
@Parcel
public class Usuario {

    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                '}';
    }

}
