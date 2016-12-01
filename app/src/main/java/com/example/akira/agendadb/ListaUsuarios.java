package com.example.akira.agendadb;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaUsuarios extends ListActivity {

    ArrayList<Usuario> dadosUsuario = new ArrayList<Usuario>();
    ArrayList<String> listItems = new ArrayList<String>();

    String update_cpf;
    String update_nome;
    String update_telefone;
    String update_rua;
    String update_bairro;
    String update_cidade;
    String update_estado;
    String lCodigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        Bundle bCodigo = null;
        bCodigo = this.getIntent().getExtras();
        lCodigo = bCodigo.getString("senha");

        Button botaoCadastro = (Button) findViewById(R.id.buttonCADASTRAR);

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });



        GetUsuarios getUsuarios = new GetUsuarios();
        try {

            getUsuarios.codigo = lCodigo;
            dadosUsuario = getUsuarios.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Usuario u: dadosUsuario){
            listItems.add(u.getNome());
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listItems));

    }

    private void cadastrar() {
        Bundle cBundle = new Bundle();
        cBundle.putString("senha", lCodigo);

        Intent intent = new Intent(this, CadastroUsuario.class);
        intent.putExtras(cBundle);
        startActivity(intent);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);

        String valorSelecionado = (String) getListAdapter().getItem(position);
        Toast.makeText(this,valorSelecionado,Toast.LENGTH_SHORT).show();
        usuarioSelecionado(valorSelecionado);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("_id", update_cpf);
        dataBundle.putString("nome", update_nome);
        dataBundle.putString("telefone", update_telefone);
        dataBundle.putString("rua", update_rua);
        dataBundle.putString("bairro", update_bairro);
        dataBundle.putString("cidade", update_cidade);
        dataBundle.putString("estado", update_estado);
        dataBundle.putString("senha", lCodigo);
        Intent expandirUserIntent = new Intent(this,AtualizarUsuario.class);
        expandirUserIntent.putExtras(dataBundle);
        startActivity(expandirUserIntent);
        finish();
    }

    private void usuarioSelecionado(String valorSelecionado) {
        for (Usuario user: dadosUsuario){
            if (valorSelecionado.contains(user.getNome())){
                update_cpf = user.getCpf_id();
                update_nome = user.getNome();
                update_telefone = user.getTelefone();
                update_rua = user.getRua();
                update_bairro = user.getBairro();
                update_cidade = user.getCidade();
                update_estado = user.getEstado();
            }
        }
    }

}
