package com.example.projeto_salodebeleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Barba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barba);
        getSupportActionBar().hide();


        ImageView telaAgenda = findViewById(R.id.idTelaAgenda);
        ImageView telaPerfil = findViewById(R.id.idTelaPerfil);

        telaAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Agenda.class);
                startActivity(it);
            }
        });
        telaPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Perfil.class);
                startActivity(it);
            }
        });

    }
}