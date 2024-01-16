package com.example.puzzle_anibalbenedicto.Niveles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.puzzle_anibalbenedicto.DatabaseHelper;
import com.example.puzzle_anibalbenedicto.R;

import java.util.Random;

import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class NivelUnoActivity extends AppCompatActivity {

    private static final int FILAS = 2;
    private static final int COLUMNAS = 4;
    private ImageButton[][] imageButtons;
    private ImageButton selectedImageButton;
    private int intentos = 0; // Contador de intentos
    private TextView textViewIntentos;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_nvl1);

        // Obtener el GridLayout que contendrá los ImageButton
        GridLayout gridLayout = findViewById(R.id.gridLayoutNivelUno);
        textViewIntentos = findViewById(R.id.textViewIntentos);
        dbHelper = new DatabaseHelper(this);
        // Crear una matriz de ImageButton
        imageButtons = new ImageButton[FILAS][COLUMNAS];

        // Obtener un arreglo de recursos de imágenes
        int[] imagenes = {
                R.drawable.imagen1,
                R.drawable.imagen2,
                R.drawable.imagen3,
                R.drawable.imagen4,
                R.drawable.imagen5,
                R.drawable.imagen6,
                R.drawable.imagen7,
                R.drawable.imagen8
        };

        // Desordenar el arreglo de imágenes
        shuffleArray(imagenes);

        // Crear y configurar los ImageButton en el GridLayout
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                imageButtons[i][j] = new ImageButton(this);
                imageButtons[i][j].setImageResource(imagenes[i * COLUMNAS + j]);
                imageButtons[i][j].setScaleType(ImageView.ScaleType.FIT_CENTER);

                // Añadir OnClickListener a cada ImageButton
                imageButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImageButtonClick((ImageButton) v);
                    }
                });

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.columnSpec = GridLayout.spec(j, 1f);
                params.rowSpec = GridLayout.spec(i, 1f);

                gridLayout.addView(imageButtons[i][j], params);
            }
        }
        updateIntentosCounter();
    }

    // Método para desordenar un arreglo
    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Intercambiar elementos
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // Método llamado al hacer clic en un ImageButton
    private void onImageButtonClick(ImageButton clickedImageButton) {
        if (selectedImageButton == null) {
            // No hay ninguna imagen seleccionada, seleccionar la actual
            selectedImageButton = clickedImageButton;
        } else {
            // Intercambiar las imágenes
            int tempResource = selectedImageButton.getImageResource();
            selectedImageButton.setImageResource(clickedImageButton.getImageResource());
            clickedImageButton.setImageResource(tempResource);

            // Incrementar el contador de intentos
            intentos++;

            // Reiniciar la imagen seleccionada
            selectedImageButton = null;

            // Actualizar el contador de intentos
            updateIntentosCounter();
            if (isPuzzleCompleted()) {
                // Guardar la puntuación en la base de datos
                saveScore();
            }
        }
    }
    // Método para actualizar el contador de intentos en el TextView
    private void updateIntentosCounter() {
        textViewIntentos.setText("Intentos: " + intentos);
    }
    private void saveScore() {
        // Obtener el nombre del jugador (puedes implementar la lógica para obtener el nombre)
        String nombreJugador = "NombrePorDefecto";

        // Guardar la puntuación en la base de datos
        dbHelper.addScoreNivelUno(nombreJugador, intentos);

        // Aquí puedes realizar acciones adicionales si lo necesitas, como mostrar un mensaje al jugador, etc.
    }
    private boolean isPuzzleCompleted() {
        // Lógica para determinar si todas las imágenes están en la posición correcta
        // (Debes implementar esta lógica según la mecánica de tu juego)
        // Puedes, por ejemplo, comparar las imágenes en la matriz imageButtons con la
        // secuencia original de imágenes desordenadas.

        // En este ejemplo simple, supondremos que el puzzle está completado si el contador
        // de intentos llega a un cierto límite (puedes ajustar esto según tus necesidades).
        return intentos <= 10; // Por ejemplo, consideramos completado después de 10 intentos
    }
}


