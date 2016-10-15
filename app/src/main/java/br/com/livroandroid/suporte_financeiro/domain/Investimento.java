package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 09/10/2016.
 */

public class Investimento implements Parcelable {

    private String nomeInvestimento;
    private BigDecimal valorInvestimento;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investimento that = (Investimento) o;

        if (!nomeInvestimento.equals(that.nomeInvestimento)) return false;
        return valorInvestimento.equals(that.valorInvestimento);

    }

    @Override
    public int hashCode() {
        int result = nomeInvestimento.hashCode();
        result = 31 * result + valorInvestimento.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.nomeInvestimento);
        dest.writeSerializable(this.valorInvestimento);
    }

    public Investimento() {
    }

    protected Investimento(android.os.Parcel in) {
        this.nomeInvestimento = in.readString();
        this.valorInvestimento = (BigDecimal) in.readSerializable();
    }

    public static final Parcelable.Creator<Investimento> CREATOR = new Parcelable.Creator<Investimento>() {
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
