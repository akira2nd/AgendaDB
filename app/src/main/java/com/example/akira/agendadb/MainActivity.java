package com.example.akira.agendadb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button botaoCadastro = (Button) findViewById(R.id.buttonCadastrar);
        Button botaoEntrar = (Button) findViewById(R.id.buttonListar);
        final EditText senha = (EditText) findViewById(R.id.editTextSENHA);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar("T"+senha.getText().toString());
            }
        });
    /**
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
     **/
    }

    private void entrar(String codigo) {
        Bundle senhaBundle = new Bundle();
        senhaBundle.putString("senha",codigo);

        Intent intent = new Intent(this, ListaUsuarios.class);
        intent.putExtras(senhaBundle);
        startActivity(intent);
    }

    /**
    private void cadastrar() {
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }
    **/

}
