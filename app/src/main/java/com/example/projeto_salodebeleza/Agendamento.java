package com.example.projeto_salodebeleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Agendamento extends AppCompatActivity {

    private Button data;
    private Button consulta;
    private Button botaoEnviar;
    private TextView textoResultado;


    private RadioGroup radioGroup;
    private RadioButton ButtonEscolhido;
    private TextView resultadoHorarioEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        getSupportActionBar().hide();

        //data
        data = findViewById(R.id.idData);
        botaoEnviar = findViewById(R.id.btnAgendamentoConfirmar);
        textoResultado = findViewById(R.id.idresData);

        //consulta
        consulta = findViewById(R.id.idConsultarAgenda);

        //horario
        radioGroup = findViewById(R.id.radioGroup);
        resultadoHorarioEscolhido = findViewById(R.id.idHorario);


        ImageView telaSalao = findViewById(R.id.idTelaAgenda);
        ImageView telaPerfil = findViewById(R.id.idTelaPerfil);

        telaSalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Principal.class);
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

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataesc = data.getText().toString();
                if (dataesc.isEmpty()) {
                    textoResultado.setText("Por favor, escolha uma data.");
                } else {
                    textoResultado.setText("Data: " + dataesc.toString());
                }

                int idRadioButtonPreferido = radioGroup.getCheckedRadioButtonId();
                if (idRadioButtonPreferido > 0) {
                    ButtonEscolhido = findViewById(idRadioButtonPreferido);
                    String dispositvos = "Horário escolhido: " + ButtonEscolhido.getText().toString();
                    resultadoHorarioEscolhido.setText(dispositvos);
                } else {
                    resultadoHorarioEscolhido.setText("Por favor, escolha um horário.");
                }

                Intent it = new Intent(getApplicationContext(), Agenda.class);
                startActivity(it);

            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "Atendimento")
                        .putExtra(CalendarContract.Events.DESCRIPTION, "Teste")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "Alto Caiçara")
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                        .putExtra(Intent.EXTRA_EMAIL, "jefinrc18@gmail.com")
                        .putExtra(CalendarContract.Events.ALL_DAY, "false");

                startActivity(intent);

            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://calendar.google.com/calendar/u/1?cid=amVmaW5yYzE4QGdtYWlsLmNvbQ")));
            }
        });
    }
}