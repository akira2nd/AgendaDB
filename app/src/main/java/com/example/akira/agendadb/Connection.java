package com.example.akira.agendadb;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Created by Akira on 13/09/2016.
 */
public class Connection extends AsyncTask<Usuario,Void,Boolean>{

    public String codigo;

    @Override
    protected Boolean doInBackground(Usuario... arg0) {
        try{
            Usuario usuario = arg0[0];

            CriarQuery criarQuery = new CriarQuery();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(criarQuery.construirUrlSalvar(codigo));

            StringEntity params = new StringEntity(criarQuery.createUsuario(usuario));
            request.addHeader("content-type", "application/json;charset=utf-8");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if(response.getStatusLine().getStatusCode()<205)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            //e.getCause();
            String val = e.getMessage();
            String val2 = val;
            return false;
        }

    }
}

