package com.museubd.model.bean;
import java.sql.Date;

public class Emprestados{
    private int num_obj4;
    private String nomeColecao;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public int getNum_obj4(){
        return this.num_obj4;
    }

    public void setNum_obj4(int num_obj4){
        this.num_obj4 = num_obj4;
    }

    public String getNomeColecao(){
        return this.nomeColecao;
    }

    public void setNomeColecao(String nomeColecao){
        this.nomeColecao = nomeColecao;
    }

    public Date getDataEmprestimo(){
        return this.dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo){
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao(){
        return this.dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }    
}
