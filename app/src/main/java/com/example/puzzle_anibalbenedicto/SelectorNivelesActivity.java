package com.example.puzzle_anibalbenedicto;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.puzzle_anibalbenedicto.Niveles.NivelUnoActivity;
import com.example.puzzle_anibalbenedicto.Niveles.NivelDosActivity;

public class SelectorNivelesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_niveles);

        Button btnNivelUno = findViewById(R.id.btnNivel1);
        Button btnNivelDos = findViewById(R.id.btnNivel2);

        btnNivelUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectorNivelesActivity.this, NivelUnoActivity.class);
                startActivity(intent);
            }
        });

        btnNivelDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectorNivelesActivity.this, NivelDosActivity.class);
                startActivity(intent);
            }
        });
    }

    }

