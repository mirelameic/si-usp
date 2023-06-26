package com.museubd.model.bean;
import java.sql.Date;

public class ObjetosArte{
    private int numID;
    private String titulo;
    private String nomeArtista;
    private String descricao;
    private Date anoCriacao;
    private String periodoArt;
    private String paisCultura;
    private String estilo;
    private String tipo;
    private String status;
    private double custo;


    public int getNumID(){
        return this.numID;
    }

    public void setNumID(int numID){
        this.numID = numID;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getNomeArtista(){
        return this.nomeArtista;
    }

    public void setNomeArtista(String nomeArtista){
        this.nomeArtista = nomeArtista;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Date getAnoCriacao(){
        return this.anoCriacao;
    }

    public void setAnoCriacao(Date anoCriacao){
        this.anoCriacao = anoCriacao;
    }

    public String getPeriodoArt(){
        return this.periodoArt;
    }

    public void setPeriodoArt(String periodoArt){
        this.periodoArt = periodoArt;
    }

    public String getPaisCultura(){
        return this.paisCultura;
    }

    public void setPaisCultura(String paisCultura){
        this.paisCultura = paisCultura;
    }

    public String getEstilo(){
        return this.estilo;
    }

    public void setEstilo(String estilo){
        this.estilo = estilo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public double getCusto() {
        return this.custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}
