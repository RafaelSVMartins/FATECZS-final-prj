package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Rrafael on 09/10/2016.
 */

public class Investimento implements Parcelable {

    private Long id;
    private String nomeInvestimento;
    private BigDecimal valorInvestimento;
    private Calendar  vencimentoInvestimento;
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Calendar getVencimentoInvestimento() {
        return vencimentoInvestimento;
    }

    public void setVencimentoInvestimento(Calendar vencimentoInvestimento) {
        this.vencimentoInvestimento = vencimentoInvestimento;
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
                "id=" + id +
                ", nomeInvestimento='" + nomeInvestimento + '\'' +
                ", valorInvestimento=" + valorInvestimento +
                ", vencimentoInvestimento=" + vencimentoInvestimento +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investimento that = (Investimento) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nomeInvestimento != null ? !nomeInvestimento.equals(that.nomeInvestimento) : that.nomeInvestimento != null)
            return false;
        if (valorInvestimento != null ? !valorInvestimento.equals(that.valorInvestimento) : that.valorInvestimento != null)
            return false;
        if (vencimentoInvestimento != null ? !vencimentoInvestimento.equals(that.vencimentoInvestimento) : that.vencimentoInvestimento != null)
            return false;
        return usuario != null ? usuario.equals(that.usuario) : that.usuario == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nomeInvestimento != null ? nomeInvestimento.hashCode() : 0);
        result = 31 * result + (valorInvestimento != null ? valorInvestimento.hashCode() : 0);
        result = 31 * result + (vencimentoInvestimento != null ? vencimentoInvestimento.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nomeInvestimento);
        dest.writeSerializable(this.valorInvestimento);
        dest.writeSerializable(this.vencimentoInvestimento);
        dest.writeParcelable(this.usuario, flags);
    }

    public Investimento() {
    }

    protected Investimento(android.os.Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nomeInvestimento = in.readString();
        this.valorInvestimento = (BigDecimal) in.readSerializable();
        this.vencimentoInvestimento = (Calendar) in.readSerializable();
        this.usuario = in.readParcelable(Usuario.class.getClassLoader());
    }

    public static final Creator<Investimento> CREATOR = new Creator<Investimento>() {
        @Override
        public Investimento createFromParcel(android.os.Parcel source) {
            return new Investimento(source);
        }

        @Override
        public Investimento[] newArray(int size) {
            return new Investimento[size];
        }
    };
}
