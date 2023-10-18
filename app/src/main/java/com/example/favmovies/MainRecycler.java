package com.example.favmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favmovies.modelo.Categoria;
import com.example.favmovies.modelo.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class MainRecycler extends AppCompatActivity {

    // identificador de intent
    public static final String PELICULA_SELECCIONADA = "pelicula_seleccionada";
    public static final String PELICULA_CREADA = "pelicula_creada";
    // identificador de activity
    private static final int GESTION_ACTIVITY = 1;

    //Modelo datos
    private List<Pelicula> listaPeli;
    private Pelicula peli;
    private RecyclerView listaPeliView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        rellenarLista();

        listaPeliView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPeliView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaPeliView.setLayoutManager(layoutManager);

        ListaPeliculasAdapter lpAdapter = new ListaPeliculasAdapter(listaPeli, new ListaPeliculasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pelicula peli) {
                clickonItem(peli);
            }
        });
        listaPeliView.setAdapter(lpAdapter);
    }

    // Creamos la lista de peliculas
    private void rellenarLista() {
        listaPeli = new ArrayList<Pelicula>();
        Categoria cataccion = new Categoria("Acción", "PelisAccion");
        Pelicula peli = new Pelicula("Tenet", "Una acción épica que gira en " + "torno al espionaje internacional, los viajes en el tiempo y la evolución, " + "en la que un agente secreto debe prevenir la Tercera Guerra Mundial.", cataccion, "150", "26/8/2020");
        Pelicula peli2 = new Pelicula("Baby Driver", "La historia sigue a un joven y talentoso conductor conocido como Baby, y que se especializa en fugas.", cataccion, "115", "11/03/2017");
        listaPeli.add(peli);
        listaPeli.add(peli2);
    }

    // Click del item del adapter
    public void clickonItem(Pelicula peli) {
        Log.i("Click adapter", "Item Clicked " + peli.getCategoria().getNombre());
        //Toast.makeText(MainActivity.this, "Item Clicked "+user.getId(), Toast.LENGTH_LONG).show();

        //Paso el modo de apertura
        Intent intent = new Intent(MainRecycler.this, MainActivity.class);
        intent.putExtra(PELICULA_SELECCIONADA, peli);

        startActivity(intent);
    }

    public void crearPeli(View v){
        Log.d("CrearPeli", "crearPeil");
        Intent intent=new Intent(MainRecycler.this, MainActivity.class);
        startActivityForResult(intent, GESTION_ACTIVITY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos a qué petición se está respondiendo
        if (requestCode == GESTION_ACTIVITY) {
            // Nos aseguramos que el resultado fue OK
            if (resultCode == RESULT_OK) {
                Pelicula peli = data.getParcelableExtra(PELICULA_CREADA);
                //Log.d("PeliCreadaRecicler", peli.getTitulo());

                // Refrescar el ReciclerView
                //Añadimos a la lista de peliculas la peli nueva
                listaPeli.add(peli);

                //creamos un nuevo adapter que le pasamos al recyclerView
                ListaPeliculasAdapter listaPeliculasAdapter = new ListaPeliculasAdapter(listaPeli, new ListaPeliculasAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Pelicula peli) {
                        clickonItem(peli);
                    }
                });
                listaPeliView.setAdapter(listaPeliculasAdapter);
            }
        }

    }
}