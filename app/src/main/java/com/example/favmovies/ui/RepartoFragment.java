package com.example.favmovies.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.favmovies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RepartoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepartoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NOMBRE = "Nombre";
    private static final String ARG_IMAGEN = "Imagen";
    private static final String ARG_URL_IMBD = "urlImbd";

    // TODO: Rename and change types of parameters
    private String nombre;
    private String imagen;
    private String urlImbd;

    public RepartoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nombre Parameter 1.
     * @param imagen Parameter 2.
     * @return A new instance of fragment ActoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepartoFragment newInstance(String nombre, String imagen, String urlImbd) {
        RepartoFragment fragment = new RepartoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_IMAGEN, imagen);
        args.putString(ARG_URL_IMBD, urlImbd);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            imagen = getArguments().getString(ARG_IMAGEN);
            urlImbd = getArguments().getString(ARG_URL_IMBD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reparto, container, false);
    }
}