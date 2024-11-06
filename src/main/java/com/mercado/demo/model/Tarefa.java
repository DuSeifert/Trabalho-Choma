package com.mercado.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titulo;
    private String descricao;
    private String data_criacao;
    private Status status;

    //Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public Status getStatus() {
        return status;
    }

    //Setters
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
