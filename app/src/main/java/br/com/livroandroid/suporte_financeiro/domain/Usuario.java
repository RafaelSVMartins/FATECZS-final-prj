package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcelable;
import android.util.Log;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Rrafael on 09/10/2016.
 */
public class Usuario implements Parcelable {

    private Long id;

    private String nome;

    private String sexo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nome.equals(usuario.nome);

    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.nome);
    }

    public Usuario() {
    }

    protected Usuario(android.os.Parcel in) {
        this.nome = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(android.os.Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
