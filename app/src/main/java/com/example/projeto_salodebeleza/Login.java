package com.example.projeto_salodebeleza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    //nome
    private EditText emailLogin, senhaLogin;
    private Button botaoEntrar, botaoTelaCadastrar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        emailLogin = findViewById(R.id.idEmailEntrar);
        senhaLogin = findViewById(R.id.idSenhaEntrar);
        botaoEntrar = findViewById(R.id.btnEntrar);
        botaoTelaCadastrar = findViewById(R.id.btnTelaCadastrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lgnEmail = emailLogin.getText().toString();
                String lgnSenha = senhaLogin.getText().toString();

                if (!TextUtils.isEmpty(lgnEmail) && !TextUtils.isEmpty(lgnSenha)) {
                    mAuth.signInWithEmailAndPassword(lgnEmail, lgnSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                abrirTelaPrincipal();
                            } else {
                                String error;
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    error = "Email ou/e senha inválido(s)";
                                } catch (Exception e) {
                                    error = "Erro ao fazer login";
                                }
                                Toast.makeText(Login.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }

        });
        botaoTelaCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(it);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent it = new Intent(getApplicationContext(), Principal.class);
        }
    }

    private void abrirTelaPrincipal() {
        Intent it = new Intent(getApplicationContext(), Principal.class);
        startActivity(it);
        finish();
    }
}