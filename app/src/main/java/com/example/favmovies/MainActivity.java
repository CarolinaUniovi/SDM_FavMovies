package com.example.favmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.favmovies.modelo.Categoria;
import com.example.favmovies.modelo.Pelicula;
import com.example.favmovies.util.Conexion;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String POS_CATEGORIA_SELECCIONADA = " ";
    public static final int GESTION_CATEGORIA = 1;
    public static final String CATEGORIA_SELECCIONADA = "categoria_seleccionada";
    public static final String CATEGORIA_MODIFICADA = "categoria_modificada";

    private final List<Categoria> listaCategorias = new ArrayList<>();
    private Snackbar msgCreaCategoria;
    private Spinner spCategoria;
    private Button btnGuardar;
    private EditText etxtTitulo;
    private EditText etxtArgumento;
    private EditText etxtDuracion;
    private EditText etxtDate;
    private ImageButton btnEdit;
    private boolean creandoCategoria = false;
    private Pelicula pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.tituloActivityEntrada);

        init();
    }

    private void init() {
        spCategoria = findViewById(R.id.spCategoria);
        btnGuardar = findViewById(R.id.btnGuardar);
        etxtTitulo = findViewById(R.id.etxtTitulo);
        etxtArgumento = findViewById(R.id.etxtArgumento);
        etxtDuracion = findViewById(R.id.etxtDuracion);
        etxtDate = findViewById(R.id.etxtDate);
        btnEdit = findViewById(R.id.btnEditCategoria);
        crearLista();
        introListaSpinner();

        Intent intentPeli = getIntent();
        Pelicula pelicula = intentPeli.getParcelableExtra(MainRecycler.PELICULA_SELECCIONADA);
        if (pelicula != null)
            abrirModoConsulta(pelicula);

        initBtnGuardar();
        initbtnEditCategoria();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.Compartir){
            Conexion conexion=new Conexion(getApplicationContext());

            if (conexion.compruebaConexion()){
                compartirPeli();
            }
            else
                Toast.makeText(getApplicationContext(), R.string.comprueba_conexion, Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
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
                spCategoria.setSelection(i);
                break;
            }
            i++;
        }

        etxtTitulo.setEnabled(false);
        etxtDate.setEnabled(false);
        etxtArgumento.setEnabled(false);
        etxtDuracion.setEnabled(false);
        btnEdit.setEnabled(false);
        btnGuardar.setEnabled(false);
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
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    guardarPeli();
                    Snackbar.make(findViewById(R.id.layoutPrincipal), R.string.msg_guardado,
                                    Snackbar.LENGTH_LONG)
                            .show();
                }

            }
        });
    }

    private void guardarPeli() {
        pelicula = new Pelicula(etxtTitulo.getText().toString(), etxtArgumento.getText().toString(),
                listaCategorias.get(spCategoria.getSelectedItemPosition()), etxtDate.getText().toString(),
                etxtDuracion.getText().toString());

        Snackbar.make(findViewById(R.id.layoutPrincipal), R.string.msg_guardado,
                        Snackbar.LENGTH_LONG)
                .show();
        Intent intentResultado = new Intent();
        intentResultado.putExtra(MainRecycler.PELICULA_CREADA, pelicula);
        setResult(RESULT_OK, intentResultado);
        finish();
    }

    private boolean validarCampos() {
        if (etxtTitulo.getText().toString().isEmpty()) {
            etxtTitulo.setError(getString(R.string.hintTitulo));
            etxtTitulo.requestFocus();
            return false;
        }
        if (etxtArgumento.getText().toString().isEmpty()) {
            etxtArgumento.setError(getString(R.string.hintArgumento));
            etxtArgumento.requestFocus();
            return false;
        }
        if (etxtDuracion.getText().toString().isEmpty()) {
            etxtDuracion.setError(getString(R.string.hintDuracion));
            etxtDuracion.requestFocus();
            return false;
        }
        if (etxtDate.getText().toString().isEmpty()) {
            etxtDate.setError(getString(R.string.hintDate));
            etxtDate.requestFocus();
            return false;
        }

        return true;
    }

    private void compartirPeli() {
        if (!validarCampos()) return;
        /* es necesario hacer un intent con la constate ACTION_SEND */
        /*Llama a cualquier app que haga un envío*/
        Intent itSend = new Intent(Intent.ACTION_SEND);
        /* vamos a enviar texto plano */
        itSend.setType("text/plain");
        // itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{para});
        itSend.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.subject_compartir) + ": " + etxtTitulo.getText().toString());
        itSend.putExtra(Intent.EXTRA_TEXT, getString(R.string.titulo)
                +": "+etxtTitulo.getText().toString()+"\n"+
                getString(R.string.argumento)
                +": "+etxtArgumento.getText().toString()
        );

        /* iniciamos la actividad */
                /* puede haber más de una aplicacion a la que hacer un ACTION_SEND,
                   nos sale un ventana que nos permite elegir una.
                   Si no lo pongo y no hay activity disponible, pueda dar un error */
        Intent shareIntent=Intent.createChooser(itSend, null);
        startActivity(shareIntent);
    }
}