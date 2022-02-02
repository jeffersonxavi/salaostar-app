package com.example.projeto_salodebeleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Agenda extends AppCompatActivity {
    private CheckBox crtTesoura;
    private CheckBox crtMaquina;
    private CheckBox crtBarba;
    private CheckBox svcCabelo;
    private CheckBox svcHidratacao;
    private TextView resultadoServicos;

    private Button botaoAgendar, botaoCorteMaquina, botaoCorteTesoura, botaoBarba;

    ImageView telaSalao, telaPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        getSupportActionBar().hide();


        crtTesoura = findViewById(R.id.idCorteTesoura);
        crtMaquina = findViewById(R.id.idCorteMaquina);
        crtBarba = findViewById(R.id.idCorteBarba);
        svcCabelo = findViewById(R.id.idLavarCabelo);
        svcHidratacao = findViewById(R.id.idHidratacao);
        resultadoServicos = findViewById(R.id.idResultadoServicos);

        botaoAgendar= findViewById(R.id.btnAgendaConfirmar);
        telaSalao= findViewById(R.id.idTelaSalao);
        telaPerfil= findViewById(R.id.idTelaPerfil);
        botaoCorteMaquina = findViewById(R.id.btnTelaMaquina);
        botaoCorteTesoura = findViewById(R.id.btnTelaTesoura);
        botaoBarba = findViewById(R.id.btnTelaBarba);

        botaoAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itensSelecionados = "";
                itensSelecionados += "Tesoura Status: " + crtTesoura.isChecked() + "\n";
                itensSelecionados += "Maquina Status: " + crtMaquina.isChecked() + "\n";
                itensSelecionados += "Barba Status: " + crtBarba.isChecked() + "\n";
                itensSelecionados += "Lavar Status: " + svcCabelo.isChecked() + "\n";
                itensSelecionados += "Hidratação Status: " + svcHidratacao.isChecked() + "\n";
                resultadoServicos.setText(itensSelecionados.toString());

                Intent it = new Intent(getApplicationContext(), Agendamento.class);
                startActivity(it);
            }
        });

        botaoBarba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Barba.class);
                startActivity(it);
            }
        });
        botaoCorteTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CorteTesoura.class);
                startActivity(it);
            }
        });
        botaoCorteMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CorteMaquina.class);
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
        telaSalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Principal.class);
                startActivity(it);
            }
        });


    }

}