package com.example.projeto_salodebeleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Principal extends AppCompatActivity {

    ImageView telaAgenda, telaPerfil;
    private FirebaseAuth mAuth;
    private Button botaoSobre;
    private Button botaoContato;
    private Button botaoAgendamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();

        botaoSobre = findViewById(R.id.btnTelaSobre);
        botaoContato = findViewById(R.id.btnTelaContato);
        botaoAgendamento = findViewById(R.id.btnTelaAgendamento);
        telaAgenda = findViewById(R.id.idTelaAgenda);
        telaPerfil = findViewById(R.id.idTelaPerfil);

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

        botaoSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TelaSobre.class);
                startActivity(it);
            }
        });

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone=5577997005579";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        botaoAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Agendamento.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent it = new Intent(getApplicationContext(), Login.class);
            startActivity(it);
            finish();
        }
    }


}