package com.example.favmovies.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.favmovies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArgumentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArgumentoFragment extends Fragment {

    /* Las variables que utilizaremos */
    private static final String ARGUMENTO_PELI = "Argumento";
    private String argumentoPeli;

    /*
        Esto es un FactoryMethod.
        Los datos están siendo enviados ANTES del onCreate.
        El Bundle permanece cuando se tiene que recrear.
     */
    public static ArgumentoFragment newInstance(String argumentoPeli) {
        ArgumentoFragment fragment = new ArgumentoFragment();
        Bundle args = new Bundle();
        //Esto no tiene mucha ciencia -> Clave, valor.
        args.putString(ARGUMENTO_PELI, argumentoPeli);
        fragment.setArguments(args);
        return fragment;
    }

    /*
        Aquí están disponibles ya los datos necesarios.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argumentoPeli = getArguments().getString(ARGUMENTO_PELI);

        }
    }

    /* Al crear la vista, cargamos los valores necesarios */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Mostramos el fragmento en el contenedor
        View root = inflater.inflate(R.layout.fragment_argumento, container, false);
        TextView tvArgumento = root.findViewById(R.id.text_argumento);
        tvArgumento.setText(argumentoPeli);
        return root;
    }
}