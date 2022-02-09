package com.example.projeto_salodebeleza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Agenda extends AppCompatActivity {

    ImageView telaSalao, telaPerfil;
    String usuarioID;
    private CheckBox crtTesoura, crtMaquina, crtBarba;
    private String tesoura = "", maquina = "", barba = "";
    private Button botaoConsulta, botaoAgendar, botaoCorteMaquina, botaoCorteTesoura, botaoBarba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        getSupportActionBar().hide();

        crtTesoura = findViewById(R.id.idCorteTesoura);
        crtMaquina = findViewById(R.id.idCorteMaquina);
        crtBarba = findViewById(R.id.idCorteBarba);

        botaoAgendar = findViewById(R.id.btnAgendaConfirmar);
        telaSalao = findViewById(R.id.idTelaSalao);
        telaPerfil = findViewById(R.id.idTelaPerfil);
        botaoCorteMaquina = findViewById(R.id.btnTelaMaquina);
        botaoCorteTesoura = findViewById(R.id.btnTelaTesoura);
        botaoBarba = findViewById(R.id.btnTelaBarba);
        botaoConsulta = findViewById(R.id.idConsultarAgenda);

        botaoAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itensSelecionados = "";
                itensSelecionados += "Tesoura Status: " + crtTesoura.isChecked() + "\n";
                itensSelecionados += "Maquina Status: " + crtMaquina.isChecked() + "\n";
                itensSelecionados += "Barba Status: " + crtBarba.isChecked() + "\n";

                if (crtMaquina.isChecked() || crtTesoura.isChecked() || crtBarba.isChecked()) {
                    if (crtMaquina.isChecked()) {
                        maquina = "Maquina - ";
                    }
                    if (crtTesoura.isChecked()) {
                        tesoura = "Tesoura - ";
                    }
                    if (crtBarba.isChecked()) {
                        barba = "Barba - ";
                    }
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Atendimento - " + maquina + tesoura + barba)
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, "Guanambi - BA")
                            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                            .putExtra(Intent.EXTRA_EMAIL, "jefinrc18@gmail.com")
                            .putExtra(CalendarContract.Events.ALL_DAY, "false")
                            .putExtra(CalendarContract.Events.HAS_ALARM, 1);


                    Intent it = new Intent(getApplicationContext(), Agendamento.class);
                    startActivity(it);
                    finish();

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                        salvarDados();
                        tesoura = "";
                        maquina = "";
                        barba = "";

                    }

                } else {
                    Toast.makeText(Agenda.this, "Selecione pelo menos um serviço!", Toast.LENGTH_SHORT).show();
                }

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

        botaoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://calendar.google.com/calendar/u/1?cid=amVmaW5yYzE4QGdtYWlsLmNvbQ")));
                String url = "https://calendar.google.com/calendar/embed?src=jefinrc18%40gmail.com&ctz=America%2FBahia";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    public void salvarDados() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> colecao = new HashMap<>();
        colecao.put("valores", maquina + tesoura + barba);
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("valores").document(usuarioID);
        documentReference.set(colecao).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("db", "Sucesso ao salvar os dados.");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error", "Error ao salvar os dados." + e.toString());

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