package com.example.favmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favmovies.modelo.Pelicula;

import java.util.List;

public class ListaPeliculasAdapter extends RecyclerView.Adapter<ListaPeliculasAdapter.PeliculaViewHolder> {

    private final OnItemClickListener listener;
    private final List<Pelicula> listaPeliculas;

    public ListaPeliculasAdapter(List<Pelicula> listaPeli, OnItemClickListener listener) {
        this.listaPeliculas = listaPeli;
        this.listener = listener;
    }

    /* Indicamos el layout a "inflar" para usar en la vista
     */
    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_recycler_view_pelicula, parent, false);
        return new PeliculaViewHolder(itemView);
    }

    /**
     * Asocia el contenido a los componentes de la vista,
     * concretamente con nuestro PeliculaViewHolder que recibimos como parámetro
     */
    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);

        holder.bindUser(pelicula, listener);
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Pelicula item);
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo;
        private final TextView fecha;
        private final ImageView imagen;

        public PeliculaViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.titulopeli);
            fecha = (TextView) itemView.findViewById(R.id.fechaestreno);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }

        // asignar valores a los componentes
        public void bindUser(final Pelicula pelicula, final OnItemClickListener listener) {
            titulo.setText(pelicula.getTitulo() + " " + pelicula.getFecha());
            //fecha.setText(pelicula.getFecha());
            fecha.setText(pelicula.getCategoria().getNombre());
            // cargar imagen

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(pelicula);
                }
            });
        }
    }
}
