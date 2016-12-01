package com.example.akira.agendadb;

import java.sql.Struct;

/**
 * Created by Akira on 17/09/2016.
 */
public class CriarQuery {


    public String getDatabaseNome(){
        return "agenda";
    }

    public String getApiKey(){
        return "KI4CgYHpgGUzh7mPf7YMmx9wxVAFnrSl";
    }

    public String getBaseUrl(){
        return "https://api.mlab.com/api/1/databases/"+getDatabaseNome()+"/collections/";
    }

    public String fimApiKey(){
        return "?apiKey="+getApiKey();
    }

    /**
     * get usuario especifico
     */
    public String fimApiKey(String docid){
        return "/"+docid+"?apiKey="+getApiKey();
    }
/**
    public String documentUsuarios(){
        return "usuarios";
    }
**/
    public String construirUrlSalvar(String codigo){
        return getBaseUrl()+codigo+fimApiKey();
    }

    public String contatosGetUrl(String codigo){
        return getBaseUrl()+codigo+fimApiKey();
    }

    public String contatosAtualizaUrl(String cpf_id, String codigo){
        return getBaseUrl()+codigo+fimApiKey(cpf_id);
    }

    public String createUsuario(Usuario user){
        return String.format("{\"nome\": \"%s\", "
                        + "\"telefone\": \"%s\", "
                        + "\"rua\": \"%s\", "
                        + "\"bairro\": \"%s\", "
                        + "\"cidade\": \"%s\", "
                        + "\"estado\": \"%s\"}, "
                        + "\"safe\" : true}",
                user.nome, user.telefone, user.rua,user.bairro,user.cidade,user.estado);
    }

    public String updateUsuario(Usuario user){
        return String.format("{\"$set\": "
                + "{\"nome\": \"%s\", "
                + "\"telefone\": \"%s\", "
                + "\"rua\": \"%s\", "
                + "\"bairro\": \"%s\", "
                + "\"cidade\": \"%s\", "
                + "\"estado\": \"%s\"}" + "}",
                user.getNome(), user.getTelefone(), user.getRua(),user.getBairro(),user.getCidade(),user.getEstado());
    }

}
