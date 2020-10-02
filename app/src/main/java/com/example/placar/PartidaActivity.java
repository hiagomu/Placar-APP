package com.example.placar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
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
    private Button bFalta1;
    private Button bFalta2;
    private TextView passe1;
    private TextView passe2;
    private Button bPasse1;
    private Button bPasse2;
    private int i;
    private CountDownTimer cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        carregaCampos();
        setContent();
        buttons();
    }

    // Carregar campos
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
        falta1 = findViewById(R.id.falta1TextView);
        falta2 = findViewById(R.id.falta2TextView);
        bFalta1 = findViewById(R.id.falta1Button);
        bFalta2 = findViewById(R.id.falta2Button);
    }

    // Setar cronomêtro e nome dos times
    private void setContent() {
        Intent intent = getIntent();
        time1.setText(intent.getStringExtra("Time1"));
        time2.setText((intent.getStringExtra("Time2")));
        int tempo = (intent.getIntExtra("Tempo", 0)*1000);
        int nGols = (intent.getIntExtra("Gols", 0));
        System.out.println(nGols);

        timerMethod(tempo, nGols);
    }

    // Método do cronomêtro
    private void timerMethod(int tempo, final int nGols) {
        cronometro = new CountDownTimer(tempo, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                crono.setText(""+(int) (millisUntilFinished/1000));
                fimGols(nGols);
            }
            @Override
            public void onFinish() {
                crono.setText("FIM!");
                fimTempo();
            }
        }.start();
    }

    // Método de incremento para gols, faltas e passes
    private void incrementButtons(Button botao, final TextView textBotao) {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = Integer.parseInt(textBotao.getText().toString());
                int n = i + 1;
                textBotao.setText("" + n);
            }
        });
    }

    // Introdução dos buttons ao método de incremento
    private void buttons() {
        incrementButtons(bGol1, gol1);
        incrementButtons(bGol2, gol2);
        incrementButtons(bFalta1, falta1);
        incrementButtons(bFalta2, falta2);
        incrementButtons(bPasse1, passe1);
        incrementButtons(bPasse2, passe2);
    }

    // Método de finalização por tempo
    private void fimTempo() {
        Intent intent = new Intent(PartidaActivity.this, ResultadoActivity.class);
        String resultado;

        if(Integer.parseInt(gol1.getText().toString()) > Integer.parseInt(gol2.getText().toString())) {
            resultado = "Vitória do " + time1.getText().toString();
            intent.putExtra("Resultado", resultado);
        } else if(Integer.parseInt(gol2.getText().toString()) > Integer.parseInt(gol1.getText().toString())) {
            resultado = "Vitória do " + time2.getText().toString();
            intent.putExtra("Resultado", resultado);
        } else {
            resultado = "Empate!";
            intent.putExtra("Resultado", resultado);
        }

        informJogo(intent);
        startActivity(intent);
    }

    // Método de finalização por gols
    private void fimGols(int nGols) {
        Intent intent = new Intent(PartidaActivity.this, ResultadoActivity.class);
        String resultado;

        if(nGols == Integer.parseInt(gol1.getText().toString())) {
            resultado = "Vitória do " + time1.getText().toString();
            cronometro.cancel();
            informJogo(intent);
            startActivity(intent);
        } else if(nGols == Integer.parseInt(gol2.getText().toString())){
            resultado = "Vitória do " + time2.getText().toString();
            cronometro.cancel();
            informJogo(intent);
            startActivity(intent);
        }
    }

    // Método de envio de informações para a próxima activity
    private void informJogo(Intent intent) {
        intent.putExtra("Placar1", gol1.getText().toString());
        intent.putExtra("Placar2", gol2.getText().toString());
        intent.putExtra("Faltas1", falta1.getText().toString());
        intent.putExtra("Placar2", falta2.getText().toString());
        intent.putExtra("Passes1", passe1.getText().toString());
        intent.putExtra("Passes2", passe2.getText().toString());
    }
}
