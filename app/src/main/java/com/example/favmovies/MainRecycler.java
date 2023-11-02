package com.example.favmovies;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favmovies.datos.AppDatabase;
import com.example.favmovies.modelo.Categoria;
import com.example.favmovies.modelo.Interprete;
import com.example.favmovies.modelo.InterpretePeliculaCrossRef;
import com.example.favmovies.modelo.Pelicula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        listaPeliView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPeliView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaPeliView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appDatabase = AppDatabase.getDatabase(this);
        cargarPeliculas();
        listaPeli = appDatabase.getPeliculaDAO().getAll();
        cargarInterpretes();

        ListaPeliculasAdapter lpAdapter = new ListaPeliculasAdapter(listaPeli, new ListaPeliculasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pelicula peli) {
                clickonItem(peli);
            }
        });
        listaPeliView.setAdapter(lpAdapter);

    }

    private void cargarPeliculas() {
        Pelicula peli;
        InputStream file;
        InputStreamReader reader;
        BufferedReader bufferedReader = null;

        try {
            file = getAssets().open("peliculas.csv");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                if (data != null && data.length == 9) {
                    peli = new Pelicula(Integer.parseInt(data[0]), data[1], data[2], new Categoria(data[3], ""), data[4],
                            data[5], data[6], data[7], data[8]);
                    appDatabase.getPeliculaDAO().add(peli);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Click del item del adapter
    public void clickonItem(Pelicula peli) {
        Log.i("Click adapter", "Item Clicked " + peli.getCategoria().getNombre());
        //Toast.makeText(MainActivity.this, "Item Clicked "+user.getId(), Toast.LENGTH_LONG).show();

        //Paso el modo de apertura
        Intent intent = new Intent(MainRecycler.this, ShowMovie.class);
        intent.putExtra(PELICULA_SELECCIONADA, peli);

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
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

    private void cargarInterpretes() {

        Interprete interprete;
        InputStream file = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            file = getAssets().open("interpretes.csv");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                if (data != null) {
                    if (data.length == 4) {
                        interprete = new Interprete(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                        //AÑADIR A LA BD.
                        appDatabase.getInterpreteDAO().add(interprete);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cargarReparto() {

        InterpretePeliculaCrossRef interpretePeliculaCrossRef;
        InputStream file = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            file = getAssets().open("peliculas-reparto.csv");
            reader = new InputStreamReader(file);
            bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                if (data != null) {
                    if (data.length == 2) {
                        interpretePeliculaCrossRef = new InterpretePeliculaCrossRef(
                                Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                        appDatabase.getInterpretePeliculaCrossRefDAO().add(interpretePeliculaCrossRef);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}