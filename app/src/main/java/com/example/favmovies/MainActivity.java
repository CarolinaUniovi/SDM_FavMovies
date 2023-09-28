package com.example.favmovies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

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
                    }
                });

                msgCreaCategoria.show();
            }
        });

    }

    private void initBtnGuardar() {
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(R.id.layoutPrincipal), R.string.msg_guardado,
                                Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }
}