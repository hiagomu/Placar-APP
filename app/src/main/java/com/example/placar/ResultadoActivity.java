package com.example.placar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;
    private TextView gols1;
    private TextView gols2;
    private TextView faltas1;
    private TextView faltas2;
    private TextView passes1;
    private TextView passes2;
    private TextView resultado;
    private Button reiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        carregaCampos();
        mostrarResultado();
        reiniciarJogo();
    }

    private void carregaCampos() {
        time1 = findViewById(R.id.timeResultado1TextView);
        time2 = findViewById(R.id.timeResultado2TextView);
        gols1 = findViewById(R.id.golResultado1TextView);
        gols2 = findViewById(R.id.golResultado2TextView);
        faltas1 = findViewById(R.id.faltaResultado1TextView);
        faltas2 = findViewById(R.id.faltaResultado2TextView);
        passes1 = findViewById(R.id.passeResultado1TextView);
        passes2 = findViewById(R.id.faltaResultado2TextView);
        resultado = findViewById(R.id.mensagemResultadoTextView);
        reiniciar = findViewById(R.id.reiniciarResultadoButton);
    }

    private void mostrarResultado() {
        Intent intent = getIntent();
        time1.setText(intent.getStringExtra("Time1"));
        time2.setText(intent.getStringExtra("Time2"));
        gols1.setText(intent.getStringExtra("Placar1"));
        gols2.setText(intent.getStringExtra("Placar2"));
        faltas1.setText(intent.getStringExtra("Faltas1"));
        faltas2.setText(intent.getStringExtra("Faltas2"));
        passes1.setText(intent.getStringExtra("Passes1"));
        passes2.setText(intent.getStringExtra("Passes2"));
        resultado.setText(intent.getStringExtra("Resultado"));
    }

    private void reiniciarJogo() {
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}