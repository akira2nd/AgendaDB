package com.example.akira.agendadb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Akira on 02/10/2016.
 */

public class GetUsuarios extends AsyncTask<Usuario, Void, ArrayList<Usuario>>{
    static BasicDBObject user = null;
    static String OriginalObjt = "";
    static String server_out = null;
    static String temp_out = null;
    public String codigo;

    @Override
    protected ArrayList<Usuario> doInBackground(Usuario... params) {
        ArrayList<Usuario> lUsuarios = new ArrayList<Usuario>();

        try {

            CriarQuery criarQuery = new CriarQuery();
            URL url = new URL(criarQuery.contatosGetUrl(codigo));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json;charset=utf-8");

            if (connection.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((temp_out = bufferedReader.readLine()) != null){
                server_out = temp_out;
            }

            //lista mongoDB
            String mongolist = "{ artificial_basicdb_list: "+server_out+"}";
            Object object = com.mongodb.util.JSON.parse(mongolist);

            DBObject dbObject = (DBObject) object;
            BasicDBList user = (BasicDBList) dbObject.get("artificial_basicdb_list");

            for (Object obj: user){
                DBObject userObj = (DBObject) obj;

                Usuario userTemp = new Usuario();
                userTemp.setCpf(userObj.get("_id").toString());
                userTemp.setNome(userObj.get("nome").toString());
                userTemp.setTelefone(userObj.get("telefone").toString());
                userTemp.setRua(userObj.get("rua").toString());
                userTemp.setBairro(userObj.get("bairro").toString());
                userTemp.setCidade(userObj.get("cidade").toString());
                userTemp.setEstado(userObj.get("estado").toString());
                lUsuarios.add(userTemp);
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return lUsuarios;
    }
}
