package com.example.projeto_salodebeleza.Model;

public class MinhaColecao {

    String id;
    String registro;

    public MinhaColecao(String registro) {
        this.registro = registro;
    }

    public MinhaColecao(String id, String registro) {
        this.id = id;
        this.registro = registro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}