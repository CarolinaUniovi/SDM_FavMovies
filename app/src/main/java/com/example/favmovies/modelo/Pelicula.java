package com.example.favmovies.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelicula implements Parcelable {

    public static final Parcelable.Creator<Pelicula> CREATOR = new Parcelable.Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };
    private String titulo;
    private String argumento;
    private Categoria categoria;
    private String duracion;
    private String fecha;
    private String urlCaratula;
    private String urlFondo;
    private String urlTrailer;

    public Pelicula(String titulo, String argumento, Categoria categoria, String duracion, String fecha,
                    String urlCaratula, String urlImagen, String urlTrailer) {
        this.titulo = titulo;
        this.argumento = argumento;
        this.categoria = categoria;
        this.duracion = duracion;
        this.fecha = fecha;
        this.urlCaratula = urlCaratula;
        this.urlFondo = urlImagen;
        this.urlTrailer = urlTrailer;
    }

    protected Pelicula(Parcel in) {
        titulo = in.readString();
        argumento = in.readString();
        categoria = in.readParcelable(Categoria.class.getClassLoader());
        duracion = in.readString();
        fecha = in.readString();
        urlCaratula = in.readString();
        urlFondo = in.readString();
        urlTrailer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(argumento);
        dest.writeParcelable(categoria, flags);
        dest.writeString(duracion);
        dest.writeString(fecha);
        dest.writeString(urlCaratula);
        dest.writeString(urlFondo);
        dest.writeString(urlTrailer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArgumento() {
        return argumento;
    }

    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrlCaratula() {
        return urlCaratula;
    }

    public void setUrlCaratula(String urlCaratula) {
        this.urlCaratula = urlCaratula;
    }

    public String getUrlFondo() {
        return urlFondo;
    }

    public void setUrlFondo(String urlFondo) {
        this.urlFondo = urlFondo;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", argumento='" + argumento + '\'' +
                ", categoria=" + categoria +
                ", duracion='" + duracion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", urlCaratula='" + urlCaratula + '\'' +
                ", urlImagen='" + urlFondo + '\'' +
                ", urlTrailer='" + urlTrailer + '\'' +
                '}';
    }
}
