package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.banco.MeuBancoDeDadosHelper;

public class MainActivity extends AppCompatActivity {

    private static MeuBancoDeDadosHelper meuBancoDeDadosHelper;
    private Button jogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuBancoDeDadosHelper = new MeuBancoDeDadosHelper(this);

        Button jogo = findViewById(R.id.jogo);
        jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }

    public static MeuBancoDeDadosHelper getMeuBancoDeDadosHelper() {
        return meuBancoDeDadosHelper;
    }

}