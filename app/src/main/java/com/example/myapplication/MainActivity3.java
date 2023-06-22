package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;

    private EditText nome;
    private Button btEnviar;
    int selecionarIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);
        nome = findViewById(R.id.logar);
        btEnviar = findViewById(R.id.buttonenviar);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecionarIMG = R.drawable.avatar2;

            }
        });
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecionarIMG= R.drawable.avatar1;

            }
        });
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecionarIMG = R.drawable.avatar3;
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = nome.getText().toString();
                if(selecionarIMG!=0 && !TextUtils.isEmpty(texto)){
                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                    intent.putExtra("image", selecionarIMG);
                    intent.putExtra("texto", texto);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Preencha a foto e o NIck", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}