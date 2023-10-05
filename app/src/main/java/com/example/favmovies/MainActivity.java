package com.example.favmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.favmovies.modelo.Categoria;
import com.example.favmovies.modelo.Pelicula;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Categoria> listaCategorias = new ArrayList<>();
    private Snackbar msgCreaCategoria;
    private Spinner spCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.tituloActivityEntrada);

        init();
    }

    private void init() {
        spCategoria = findViewById(R.id.spCategoria);
        crearLista();
        introListaSpinner();
        Intent intentPeli = getIntent();
        Pelicula pelicula = intentPeli.getParcelableExtra(MainRecycler.PELICULA_SELECCIONADA);
        if (pelicula != null)
            abrirModoConsulta(pelicula);

        initBtnGuardar();
        initbtnEditCategoria();
    }

    private void introListaSpinner() {
        // Creamos un nuevo array sólo con los nombres de las categorías
        ArrayList<String> nombres = new ArrayList<String>();
        // nombres.add("Sin definir");
        for (Categoria elemento : listaCategorias) {
            nombres.add(elemento.getNombre());
        }
        // Crea un ArrayAdapter usando un array de strings y el layout por defecto del spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, nombres);
        // Especifica el layout para usar cuando aparece la lista de elecciones
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adapter al spinner
        spCategoria.setAdapter(adapter);
    }

    private void crearLista() {
        listaCategorias.add(new Categoria("Sin definir", ""));
        listaCategorias.add(new Categoria("Acción", "Peliculas de acción"));
        listaCategorias.add(new Categoria("Comedia", "Peliculas de comedia"));
        listaCategorias.add(new Categoria("Bélica", "Peliculas de guerra"));
        listaCategorias.add(new Categoria("Aventura", "Peliculas de aventura"));
        listaCategorias.add(new Categoria("Musicales", "Peliculas musicales"));
        listaCategorias.add(new Categoria("Drama", "Peliculas de drama"));
        listaCategorias.add(new Categoria("Terror", "Peliculas de terror"));
        listaCategorias.add(new Categoria("Animación", "Peliculas de animación"));
        listaCategorias.add(new Categoria("Romance", "Peliculas de romance"));
    }

    private void abrirModoConsulta(Pelicula pelicula) {
        Button btnGuardar = findViewById(R.id.btnGuardar);
        EditText etxtTitulo = findViewById(R.id.etxtTitulo);
        EditText etxtArgumento = findViewById(R.id.etxtArgumento);
        EditText etxtDuracion = findViewById(R.id.etxtDuracion);
        EditText etxtDate = findViewById(R.id.etxtDate);
        ImageButton btnEdit = findViewById(R.id.btnEditCategoria);

        etxtTitulo.setText(pelicula.getTitulo());
        etxtDate.setText(pelicula.getFecha());
        etxtArgumento.setText(pelicula.getArgumento());
        etxtDuracion.setText(pelicula.getDuracion());
        //Busqueda en la lista de categoria para colocar la posición del spinner
        int i = 0;
        String nombreaccion = pelicula.getCategoria().getNombre();
        Log.i("CategoriaAbrirModoConsulta", nombreaccion);

        for (Categoria elemento : listaCategorias) {
            if (elemento.getNombre().equals(nombreaccion)) {
                break;
            }
            i++;
        }
        spCategoria.setSelection(i);


        etxtTitulo.setEnabled(false);
        etxtDate.setEnabled(false);
        etxtArgumento.setEnabled(false);
        etxtDuracion.setEnabled(false);
        btnEdit.setVisibility(View.INVISIBLE);
        btnGuardar.setVisibility(View.INVISIBLE);
        spCategoria.setEnabled(false);
    }

    private void initbtnEditCategoria() {
        ImageButton btnEditCategoria = findViewById(R.id.btnEditCategoria);
        btnEditCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner = findViewById(R.id.spCategoria);
                if (spinner.getSelectedItemPosition() == 0) {
                    msgCreaCategoria = Snackbar.make(findViewById(R.id.layoutPrincipal),
                            R.string.msg_crear_nueva_categoria, Snackbar.LENGTH_LONG);
                } else {
                    msgCreaCategoria = Snackbar.make(findViewById(R.id.layoutPrincipal),
                            R.string.msg_crear_modifica_categoria, Snackbar.LENGTH_LONG);
                }

                msgCreaCategoria.setAction(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(findViewById(R.id.layoutPrincipal),
                                        R.string.msg_accion_realizada, Snackbar.LENGTH_LONG)
                                .show();
                        showModificarCategoria();
                    }
                });

                msgCreaCategoria.show();
            }
        });

    }

    private void showModificarCategoria() {
        Intent categoriaIntent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(categoriaIntent);
    }

    private void initBtnGuardar() {
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAllFilled()) {
                    Snackbar.make(findViewById(R.id.layoutPrincipal), R.string.msg_guardado,
                                    Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    Snackbar.make(findViewById(R.id.layoutPrincipal), R.string.msg_error,
                                    Snackbar.LENGTH_LONG)
                            .show();
                }
            }

            private boolean isAllFilled() {
                List<EditText> lista = new ArrayList<>();
                lista.add(findViewById(R.id.etxtTitulo));
                lista.add(findViewById(R.id.etxtArgumento));
                lista.add(findViewById(R.id.etxtDuracion));
                lista.add(findViewById(R.id.etxtDate));
                return lista.stream().allMatch(l -> !l.getText().toString().isEmpty());
            }
        });
    }
}