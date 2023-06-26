package com.museubd.model.bean;
import java.sql.Date;

public class Permanentes{
    private int num_obj5;
    private String emExposicao;
    private Date dataAquisicao;

    public int getNum_obj5(){
        return this.num_obj5;
    }

    public void setNum_obj5(int num_obj5){
        this.num_obj5 = num_obj5;
    }

    public String isEmExposicao(){
        return this.emExposicao;
    }

    public String getEmExposicao(){
        return this.emExposicao;
    }

    public void setEmExposicao(String emExposicao){
        this.emExposicao = emExposicao;
    }

    public Date getDataAquisicao(){
        return this.dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao){
        this.dataAquisicao = dataAquisicao;
    }
}
