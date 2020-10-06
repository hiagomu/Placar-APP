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
    private int nGols;
    private int tempo;
    private CountDownTimer cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        carregaCampos();
        setTimes();
        getGols();
        tempoTotal();
        buttons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gol1.setText("0");
        gol2.setText("0");
        falta1.setText("0");
        falta2.setText("0");
        passe1.setText("0");
        passe2.setText("0");
        timerMethod(tempo);
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

    // Set Times
    private void setTimes() {
        Intent intent = getIntent();
        time1.setText(intent.getStringExtra("Time1"));
        time2.setText((intent.getStringExtra("Time2")));
    }

    // Get Gols
    private void getGols() {
        Intent intent = getIntent();
        nGols = (intent.getIntExtra("Gols", 0));
    }

    // Set Tempo
    public void tempoTotal() {
        Intent intent = getIntent();
        tempo = (intent.getIntExtra("Tempo", 0)*1000);
    }

    // Método do cronomêtro
    private void timerMethod(int tempo) {
        cronometro = new CountDownTimer(tempo, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                crono.setText(""+(int) (millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                crono.setText("FIM!");
                fimJogo();
            }
        }.start();
    }

    // Método de finalização
    private void fimJogo() {
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

        cronometro.cancel();
        informJogo(intent);
        startActivity(intent);
    }

    // Método de incremento para faltas e passes
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

    // Método de incremento para gols
    private void incrementButtonsGols(Button botao, final TextView textBotao) {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = Integer.parseInt(textBotao.getText().toString());
                int n = i + 1;
                textBotao.setText("" + n);
                if (n == nGols) {
                    fimJogo();
                }
            }
        });
    }

    // Introdução dos buttons ao método de incremento
    private void buttons() {
        incrementButtonsGols(bGol1, gol1);
        incrementButtonsGols(bGol2, gol2);
        incrementButtons(bFalta1, falta1);
        incrementButtons(bFalta2, falta2);
        incrementButtons(bPasse1, passe1);
        incrementButtons(bPasse2, passe2);
    }

    // Método de envio de informações para a próxima activity
    private void informJogo(Intent intent) {
        intent.putExtra("Time1", time1.getText().toString());
        intent.putExtra("Time2", time2.getText().toString());
        intent.putExtra("Placar1", gol1.getText().toString());
        intent.putExtra("Placar2", gol2.getText().toString());
        intent.putExtra("Faltas1", falta1.getText().toString());
        intent.putExtra("Faltas2", falta2.getText().toString());
        intent.putExtra("Passes1", passe1.getText().toString());
        intent.putExtra("Passes2", passe2.getText().toString());
    }

}
