package com.example.favmovies.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.favmovies.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    private static final String ARG_CATEGORIA = "Categoria";
    private static final String ARG_FECHA = "Fecha";
    private static final String ARG_DURACION = "Duracion";
    private static final String ARG_CARATULA = "Caratula";

    private String categoriaPeli;
    private String fechaPeli;
    private String duracionPeli;
    private String caratulaPeli;

    private InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String categoria, String fecha, String duracion, String caratula) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORIA, categoria);
        args.putString(ARG_FECHA, fecha);
        args.putString(ARG_DURACION, duracion);
        args.putString(ARG_CARATULA, caratula);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoriaPeli = getArguments().getString(ARG_CATEGORIA);
            fechaPeli = getArguments().getString(ARG_FECHA);
            duracionPeli = getArguments().getString(ARG_DURACION);
            caratulaPeli = getArguments().getString(ARG_CARATULA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        TextView tvCategoria = root.findViewById(R.id.txtCategoria);
        TextView tvFecha = root.findViewById(R.id.txtFecha);
        TextView tvDuracion = root.findViewById(R.id.txtDuracion);
        ImageView ivCaratula = root.findViewById(R.id.imgCaratula);

        tvCategoria.setText(categoriaPeli);
        tvFecha.setText(fechaPeli);
        tvDuracion.setText(duracionPeli);
        Picasso.get()
                .load(caratulaPeli).into(ivCaratula);

        return root;
    }
}