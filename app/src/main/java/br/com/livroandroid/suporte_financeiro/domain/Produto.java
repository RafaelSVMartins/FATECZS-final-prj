package br.com.livroandroid.suporte_financeiro.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Rrafael on 03/12/2016.
 */

public class Produto implements Parcelable {
    private Long id;
    private String NomeProduto;
    private Long QuantidadeProduto;

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

    public Long getQuantidadeProduto() {
        return QuantidadeProduto;
    }

    public void setQuantidadeProduto(Long quantidadeProduto) {
        QuantidadeProduto = quantidadeProduto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "NomeProduto='" + NomeProduto + '\'' +
                ", QuantidadeProduto=" + QuantidadeProduto +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.NomeProduto);
        dest.writeValue(this.QuantidadeProduto);
    }

    public Produto() {
    }

    protected Produto(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.NomeProduto = in.readString();
        this.QuantidadeProduto = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Produto> CREATOR = new Parcelable.Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel source) {
            return new Produto(source);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };
}
