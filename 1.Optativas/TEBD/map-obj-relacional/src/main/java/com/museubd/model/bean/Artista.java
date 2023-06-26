package com.museubd.model.bean;
import java.sql.Date;

public class Artista{
    private String nome;
    private String descricao;
    private String estiloPrincipal;
    private String periodoArt;
    private String paisOrig;
    private Date dataNasc;
    private Date dataMorte;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getEstiloPrincipal(){
        return this.estiloPrincipal;
    }

    public void setEstiloPrincipal(String estiloPrincipal){
        this.estiloPrincipal = estiloPrincipal;
    }

    public String getPeriodoArt(){
        return this.periodoArt;
    }

    public void setPeriodoArt(String estiloArt){
        this.periodoArt = estiloArt;
    }

    public String getPaisOrig(){
        return this.paisOrig;
    }

    public void setPaisOrig(String paisOrig){
        this.paisOrig = paisOrig;
    }

    public Date getDataNasc(){
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNassc){
        this.dataNasc = dataNassc;
    }

    public Date getDataMorte(){
        return this.dataMorte;
    }

    public void setDataMorte(Date dataMorte){
        this.dataMorte = dataMorte;
    }
    
}
