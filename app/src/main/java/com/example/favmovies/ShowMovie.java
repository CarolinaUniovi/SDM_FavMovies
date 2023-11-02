package com.example.favmovies;

import static com.example.favmovies.modelo.Pelicula.URL_IMAGEN_INTERPRETER;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.favmovies.modelo.Pelicula;
import com.example.favmovies.ui.ArgumentoFragment;
import com.example.favmovies.ui.InfoFragment;
import com.example.favmovies.ui.RepartoFragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowMovie extends AppCompatActivity {

    private CollapsingToolbarLayout toolBarLayout;
    private Pelicula pelicula;
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /* Cuando se selecciona uno de los botones / ítems*/
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (pelicula == null)
                return false;

            int itemId = item.getItemId();

            /* Según el caso, crearemos un Fragmento u otro */
            if (itemId == R.id.navigation_argumento) {
                /* Haciendo uso del FactoryMethod pasándole todos los parámetros necesarios */

                /* Argumento solamente necesita.... El argumento de la película */
                ArgumentoFragment argumentoFragment = ArgumentoFragment.newInstance
                        (pelicula.getArgumento());
                /* ¿Qué estaremos haciendo aquí? */
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, argumentoFragment).commit();
                return true;
            }

            if (itemId == R.id.navigation_reparto) {
                RepartoFragment repartoFragment = RepartoFragment.newInstance
                        ("", "", "");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, repartoFragment).commit();
                return true;
            }

            if (itemId == R.id.navigation_info) {
                InfoFragment infoFragment = InfoFragment.newInstance
                        (pelicula.getCategoria().getNombre(), pelicula.getFecha(),
                                pelicula.getDuracion(), URL_IMAGEN_INTERPRETER + pelicula.getUrlCaratula());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, infoFragment).commit();
                return true;
            }
            //Si no es nula y no entra... Algo falla.
            throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

    };
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);

        //Recepción datos como activity secundaria
        Intent intentPeli = getIntent();
        pelicula = intentPeli.getParcelableExtra(MainRecycler.PELICULA_SELECCIONADA);

        getComponents();

        if (pelicula != null) {
            mostrarDatos(pelicula);

        }
        initFab();
    }

    private void initFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                verTrailer(pelicula.getUrlTrailer());
            }
        });
    }

    private void getComponents() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(pelicula.getTitulo());

        // Gestión de los controles que contienen los datos de la película
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Abre una activity con YouTube y muestra el vídeo indicado en el parámetro
     *
     * @param urlTrailer url con el vídeo que se quiere visualizar
     */
    private void verTrailer(String urlTrailer) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlTrailer)));
    }

    private void mostrarDatos(Pelicula pelicula) {
        InfoFragment infoFragment = InfoFragment.newInstance(pelicula.getCategoria().getNombre(), pelicula.getFecha(),
                pelicula.getDuracion(), URL_IMAGEN_INTERPRETER + pelicula.getUrlCaratula());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, infoFragment).commit();
    }
}