package com.museubd.model.bean;

public class Pinturas{
    private int num_obj1;
    private String tipoTinta;
    private String suporte;
    
    public int getNum_obj1(){
        return this.num_obj1;
    }

    public void setNum_obj1(int num_obj1){
        this.num_obj1 = num_obj1;
    }

    public String getTipoTinta(){
        return this.tipoTinta;
    }

    public void setTipoTinta(String tipoTinta){
        this.tipoTinta = tipoTinta;
    }

    public String getSuporte(){
        return this.suporte;
    }

    public void setSuporte(String suporte){
        this.suporte = suporte;
    }
}

