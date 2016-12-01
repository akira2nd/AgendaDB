package com.example.akira.agendadb;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class AtualizarUsuario extends Activity {

    EditText editText_cpf;
    EditText editText_nome;
    EditText editText_telefone;
    EditText editText_rua;
    EditText editText_bairro;
    EditText editText_cidade;
    EditText editText_estado;

    String _id;
    String nome;
    String telefone;
    String rua;
    String bairro;
    String cidade;
    String estado;

    String aCodigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_usuario);

        Button botaoMap = (Button) findViewById(R.id.buttonMAP);

        botaoMap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map();
            }
        });

        //editText_cpf = (EditText) findViewById(R.id.editTextUcpf);
        editText_nome = (EditText) findViewById(R.id.editTextUnome);
        editText_telefone = (EditText) findViewById(R.id.editTextUtelefone);
        editText_rua = (EditText) findViewById(R.id.editTextUrua);
        editText_bairro = (EditText) findViewById(R.id.editTextUbairro);
        editText_cidade = (EditText) findViewById(R.id.editTextUcidade);
        editText_estado = (EditText) findViewById(R.id.editTextUestado);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        _id = getBundle.getString("_id");
        nome = getBundle.getString("nome");
        telefone = getBundle.getString("telefone");
        rua = getBundle.getString("rua");
        bairro = getBundle.getString("bairro");
        cidade = getBundle.getString("cidade");
        estado = getBundle.getString("estado");
        aCodigo = getBundle.getString("senha");

        //editText_cpf.setText(_id);
        editText_nome.setText(nome);
        editText_telefone.setText(telefone);
        editText_rua.setText(rua);
        editText_bairro.setText(bairro);
        editText_cidade.setText(cidade);
        editText_estado.setText(estado);

        final Button atualizaSalvar = (Button) findViewById(R.id.buttonUsalvar);
        atualizaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    atualizaUsuario(v);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void map() {
        Bundle bundle = new Bundle();
        bundle.putString("endereco", rua+","+bairro+","+cidade+","+estado);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void atualizaUsuario(View v) throws UnknownHostException {
        Usuario user = new Usuario();

        user.setCpf(_id);
        user.setNome(editText_nome.getText().toString());
        user.setTelefone(editText_telefone.getText().toString());
        user.setRua(editText_rua.getText().toString());
        user.setBairro(editText_bairro.getText().toString());
        user.setCidade(editText_cidade.getText().toString());
        user.setEstado(editText_estado.getText().toString());

        MongoLabUpdateContact tsk = new MongoLabUpdateContact();
        tsk.execute(user);

        Bundle atBundle = new Bundle();
        atBundle.putString("senha", aCodigo);

        Intent i = new Intent(this, ListaUsuarios.class);
        i.putExtras(atBundle);
        startActivity(i);
        Toast.makeText(this,"Atualizado",Toast.LENGTH_SHORT).show();
        finish();
    }


    final class MongoLabUpdateContact extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            Usuario usuario = (Usuario) params[0];

            try {

                CriarQuery qb = new CriarQuery();
                URL url = new URL(qb.contatosAtualizaUrl(usuario.getCpf_id(),aCodigo));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
                connection.setRequestProperty("Accept", "application/json;charset=utf-8");

                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());

                osw.write(qb.updateUsuario(usuario));
                osw.flush();
                osw.close();
                if (connection.getResponseCode() < 205) {
                    return true;
                } else {
                    return false;

                }

            } catch (Exception e) {
                e.getMessage();
                return false;

            }
        }

    }
}