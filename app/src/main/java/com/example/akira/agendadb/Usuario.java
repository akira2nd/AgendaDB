package com.example.akira.agendadb;

/**
 * Created by Akira on 17/09/2016.
 */
public class Usuario {

    public String cpf_id;
    public String nome;
    public String telefone;
    public String rua;
    public String bairro;
    public String cidade;
    public String estado;

    public String getCpf_id() {
        return cpf_id;
    }

    public void setCpf(String cpf) {
        this.cpf_id = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
