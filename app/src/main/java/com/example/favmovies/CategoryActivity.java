package com.example.favmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle(R.string.tituloActivityCategory);

        initBtnCancel();
        initBtnOk();
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