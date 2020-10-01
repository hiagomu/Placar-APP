package com.example.placar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PartidaActivity extends AppCompatActivity {

    private TextView crono;
    private TextView time1;
    private TextView time2;

    private TextView gol1;
    private TextView gol2;
    private Button bGol1;
    private Button bGol2;

    private TextView falta1;
    private TextView falta2;
    private Button bfalta1;
    private Button bfalta2;

    private TextView passe1;
    private TextView passe2;
    private Button bPasse1;
    private Button bPasse2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        carregaCampos();
        setPreContent();
    }

    private void carregaCampos() {
        crono = findViewById(R.id.cronoTextView);
        time1 = findViewById(R.id.time1TextView);
        time2 = findViewById(R.id.time2TextView);

        gol1 = findViewById(R.id.gol1TextView);
        gol2 = findViewById(R.id.gol2TextView);
        bGol1 = findViewById(R.id.gol1Button);
        bGol2 = findViewById(R.id.gol2Button);

        passe1 = findViewById(R.id.passe1TextView);
        passe2 = findViewById(R.id.passe2TextView);
        bPasse1 = findViewById(R.id.passe1Button);
        bPasse2 = findViewById(R.id.passe2Button);
    }
    private void setPreContent() {
        Intent intent = getIntent();
        time1.setText(intent.getStringExtra("Time1"));
        time2.setText((intent.getStringExtra("Time2")));
    }
}
