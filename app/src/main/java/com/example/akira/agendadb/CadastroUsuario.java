package com.example.akira.agendadb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroUsuario extends AppCompatActivity {

    EditText editText_cpf_id;
    EditText editText_nome;
    EditText editText_phone;
    EditText editText_rua;
    EditText editText_bairro;
    EditText editText_cidade;
    EditText editText_estado;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        Bundle cCodigo = null;
        cCodigo = this.getIntent().getExtras();
        codigo = cCodigo.getString("senha");

        //editText_cpf_id = (EditText) findViewById(R.id.editTextCPF);
        editText_nome = (EditText) findViewById(R.id.editTextNome);
        editText_phone = (EditText) findViewById(R.id.editTextPhone);
        editText_rua = (EditText) findViewById(R.id.editTextRUA);
        editText_bairro = (EditText) findViewById(R.id.editTextBAIRRO);
        editText_cidade = (EditText) findViewById(R.id.editTextCIDADE);
        editText_estado = (EditText) findViewById(R.id.editTextESTADO);

        Button save = (Button) findViewById(R.id.buttonSalvarNovo);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarNovo();
            }
        });



    }

    private void salvarNovo() {
        Usuario usuario = new Usuario();
        //usuario.cpf_id = editText_cpf_id.getText().toString();
        usuario.nome = editText_nome.getText().toString();
        usuario.telefone = editText_phone.getText().toString();
        usuario.rua = editText_rua.getText().toString();
        usuario.bairro = editText_bairro.getText().toString();
        usuario.cidade = editText_cidade.getText().toString();
        usuario.estado = editText_estado.getText().toString();

        Connection connection = new Connection();
        connection.codigo = codigo;
        connection.execute(usuario);


        finish();
    }
}
