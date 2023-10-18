package com.example.favmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.favmovies.modelo.Categoria;
import com.google.android.material.snackbar.Snackbar;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle(R.string.tituloActivityCategory);

        getData();
        initBtnCancel();
        initBtnOk();
    }

    private void getData() {
        Intent intent = getIntent();
        Log.i("MainActivity.POS_CATEGORIA_SELECCIONADA", MainActivity.POS_CATEGORIA_SELECCIONADA + " ");
        int posCategoria = intent.getIntExtra(MainActivity.POS_CATEGORIA_SELECCIONADA, 0);
        Log.i("posCategoria", posCategoria + " ");
        Categoria categEntrada = null;

        if (posCategoria > 0)
            categEntrada = intent.getParcelableExtra(MainActivity.CATEGORIA_SELECCIONADA);

        TextView textViewCrea = findViewById(R.id.txtTituloCat);
        EditText editNomCategoria = findViewById(R.id.etxtCategoria);
        EditText editDescripcion =  findViewById(R.id.etxtDescripcion);

        if (posCategoria == 0)
            textViewCrea.setText(R.string.tituloCat);
        else {

            textViewCrea.setText(R.string.modificacionCategoria);
            editNomCategoria.setText(categEntrada.getNombre());
            editDescripcion.setText(categEntrada.getDescripcion());
            // no dejamos cambiar el nombre de la categoría
            editNomCategoria.setEnabled(false);
        }
    }

    private void initBtnOk() {
        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });
    }

    private void showMainActivity() {
        Intent mainIntent = new Intent(CategoryActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void initBtnCancel() {
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });
    }
}