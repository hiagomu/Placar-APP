package com.example.placar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private EditText tempo;
    private EditText time1;
    private EditText time2;
    private EditText ngols;
    private Button iniciar;
    private Switch onOffSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregaCampos();
        switchOn();
        cliqueBotao();
    }

    @Override
    protected void onResume() {
        super.onResume();
            tempo.setText("");
            time1.setText("");
            time2.setText("");
            ngols.setText("");
            onOffSwitch.setChecked(false);
            tempo.requestFocus();
    }

    private void carregaCampos() {
        tempo = findViewById(R.id.tempoEditText);
        time1 = findViewById(R.id.time1EditText);
        time2 = findViewById(R.id.time2EditText);
        ngols = findViewById(R.id.ngolsEditText);
        iniciar = findViewById(R.id.iniciarButton);
        onOffSwitch  = (Switch)  findViewById(R.id.golsSwitch);
    }

    private void switchOn() {
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ngols.setVisibility(View.VISIBLE);
                    if (ngols.getText().toString().isEmpty() || Integer.parseInt(ngols.getText().toString()) == 0) {
                        ngols.setError("Insira quantidade de gols");
                    }
                } else {
                    ngols.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private boolean validaCampos() {
        if (tempo.getText().toString().isEmpty() || Integer.parseInt(tempo.getText().toString()) == 0) {
            tempo.setError("Insira a duração");
            return false;
        }
        if (time1.getText().toString().isEmpty()) {
            time1.setError("Insira o nome do time");
            return false;
        }
        if (time2.getText().toString().isEmpty()) {
            time2.setError("Insira o nome do time");
            return false;
        }
        return true;
    }

    private void cliqueBotao() {
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validaCampos()) return;

                int nTempo = Integer.parseInt(tempo.getText().toString());
                String nomeTime1 = time1.getText().toString();
                String nomeTime2 = time2.getText().toString();
                // Se o switch estiver acionado enviar numGols
                int numGols = Integer.parseInt(ngols.getText().toString());

                enviarValores(nTempo, nomeTime1, nomeTime2, numGols);
            }
        });
    }

    private void enviarValores(int nTempo, String nomeTime1, String nomeTime2, int numGols) {
        Intent intent = new Intent(MainActivity.this, PartidaActivity.class);
        intent.putExtra("Tempo", nTempo);
        intent.putExtra("Time1", nomeTime1);
        intent.putExtra("Time2", nomeTime2);
        intent.putExtra("Gols", numGols);
        startActivity(intent);
    }




}
