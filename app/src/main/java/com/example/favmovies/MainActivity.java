package com.example.favmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Snackbar msgCreaCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.tituloActivityEntrada);

        initBtnGuardar();
        initbtnEditCategoria();
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