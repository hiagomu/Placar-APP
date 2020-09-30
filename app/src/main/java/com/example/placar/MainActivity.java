package com.example.placar;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempo = findViewById(R.id.tempoEditText);
        time1 = findViewById(R.id.time1EditText);
        time2 = findViewById(R.id.time2EditText);
        Switch onOffSwitch = (Switch)  findViewById(R.id.golsSwitch);
        ngols = findViewById(R.id.ngolsEditText);
        iniciar = findViewById(R.id.iniciarButton);

        if(tempo.getText().toString().isEmpty() || Integer.parseInt(tempo.getText().toString()) ==  0){
            tempo.setError("Insira a duração");
        }
        if(time1.getText().toString().isEmpty()) {
            time1.setError("Insira o nome do time");
        }
        if(time2.getText().toString().isEmpty()) {
            time2.setError("Insira o nome do time");
        }

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
}
