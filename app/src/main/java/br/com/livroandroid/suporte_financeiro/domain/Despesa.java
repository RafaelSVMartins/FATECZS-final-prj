package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Rrafael on 09/10/2016.
 */

public class Despesa implements Parcelable {

    private String nomeDespesa;
    private BigDecimal valorDespesa;
    private Calendar DataVencimento;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Calendar getDataVencimento() {
        return DataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        DataVencimento = dataVencimento;
    }

    @Override
    public String toString() {
        return "despesa{" +
                "nomeDespesa='" + nomeDespesa + '\'' +
                ", valorDespesa=" + valorDespesa +
                ", DataVencimento=" + DataVencimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Despesa despesa = (Despesa) o;

        if (!nomeDespesa.equals(despesa.nomeDespesa)) return false;
        if (!valorDespesa.equals(despesa.valorDespesa)) return false;
        return DataVencimento.equals(despesa.DataVencimento);

    }

    @Override
    public int hashCode() {
        int result = nomeDespesa.hashCode();
        result = 31 * result + valorDespesa.hashCode();
        result = 31 * result + DataVencimento.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.nomeDespesa);
        dest.writeSerializable(this.valorDespesa);
        dest.writeSerializable(this.DataVencimento);
    }

    public Despesa() {
    }

    protected Despesa(android.os.Parcel in) {
        this.nomeDespesa = in.readString();
        this.valorDespesa = (BigDecimal) in.readSerializable();
        this.DataVencimento = (Calendar) in.readSerializable();
    }

    public static final Parcelable.Creator<Despesa> CREATOR = new Parcelable.Creator<Despesa>() {
        @Override
        public Despesa createFromParcel(android.os.Parcel source) {
            return new Despesa(source);
        }

        @Override
        public Despesa[] newArray(int size) {
            return new Despesa[size];
        }
    };
}
