package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 09/10/2016.
 */

public class Renda implements Parcelable {

    private Long id;
    private String nomeRenda;
    private BigDecimal valorRenda;
    private String tipoRenda;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoRenda() {
        return tipoRenda;
    }

    public void setTipoRenda(String tipoRenda) {
        this.tipoRenda = tipoRenda;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.nomeRenda);
        dest.writeSerializable(this.valorRenda);
    }

    public Renda() {
    }

    protected Renda(android.os.Parcel in) {
        this.nomeRenda = in.readString();
        this.valorRenda = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Renda> CREATOR = new Parcelable.Creator<Renda>() {
        @Override
        public Renda createFromParcel(android.os.Parcel source) {
            return new Renda(source);
        }

        @Override
        public Renda[] newArray(int size) {
            return new Renda[size];
        }
    };
}
