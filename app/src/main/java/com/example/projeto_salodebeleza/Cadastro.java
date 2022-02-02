package com.example.projeto_salodebeleza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    private EditText nomeCadastro;
    private EditText emailCadastro;
    private EditText senhaCadastro;
    private EditText senhaCadastroConfirmar;

    private Button botaoCadastrar;
    private Button botaoTelaLogin;

    private FirebaseAuth mAuth;

    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();

        nomeCadastro = findViewById(R.id.idNomeCadastrar);
        emailCadastro = findViewById(R.id.idEmailCadastrar);
        senhaCadastro = findViewById(R.id.idSenhaCadastrar);
        senhaCadastroConfirmar = findViewById(R.id.idSenhaCadastroConfirmar);

        botaoCadastrar = findViewById(R.id.btnCadastrar);
        botaoTelaLogin = findViewById(R.id.btnTelaLoginId);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registerNome = nomeCadastro.getText().toString();
                String registerEmail = emailCadastro.getText().toString();
                String registerSenha = senhaCadastro.getText().toString();
                String registerSenhaConfirmar = senhaCadastroConfirmar.getText().toString();


                if (!TextUtils.isEmpty(registerNome) && !TextUtils.isEmpty(registerEmail) && !TextUtils.isEmpty(registerSenha) && !TextUtils.isEmpty(registerSenhaConfirmar)) {
                    if (registerSenha.equals(registerSenhaConfirmar)) {
                        mAuth.createUserWithEmailAndPassword(registerEmail, registerSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    salvarDados();
                                    abrirTelaPrincipal();
                                } else {
                                    String error;
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        error = "Senha deve conter ao menos 6 carateres";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        error = "Email inválido";
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        error = "Email já inserido";
                                    } catch (Exception e) {
                                        error = "Erro ao efetuar o cadastro";
                                    }
                                    Toast.makeText(Cadastro.this, error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Cadastro.this, "As senhas devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botaoTelaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Login.class);
                startActivity(it);
                finish();
            }
        });

    }

    private void salvarDados() {
        String nome = nomeCadastro.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void abrirTelaPrincipal() {
        Intent it = new Intent(getApplicationContext(), Login.class);
        startActivity(it);
        finish();
    }
}