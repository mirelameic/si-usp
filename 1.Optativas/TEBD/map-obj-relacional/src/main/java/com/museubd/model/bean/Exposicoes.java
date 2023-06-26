package com.museubd.model.bean;
import java.sql.Date;

public class Exposicoes{
    private String nomeExposicao;
    private Date dataInicio;
    private Date dataFinal;

    public String getNomeExposicao(){
        return this.nomeExposicao;
    }

    public void setNomeExposicao(String nomeExposicao){
        this.nomeExposicao = nomeExposicao;
    }

    public Date getDataInicio(){
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio){
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal(){
        return this.dataFinal;
    }

    public void setDataFinal(Date dataFinal){
        this.dataFinal = dataFinal;
    }    
}
