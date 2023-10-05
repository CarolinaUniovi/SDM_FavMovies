package com.example.favmovies.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Categoria implements Parcelable {

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
    private final String nombre;
    private final String descripcion;

    public Categoria(String name, String descripcion) {
        this.descripcion = descripcion;
        this.nombre = name;
    }

    protected Categoria(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
    }

    public String getNombre() {
        return nombre;
    }
}
