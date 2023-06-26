package com.museubd.model.bean;

public class Colecao{
    private String nomeColecao;
    private String descricao;
    private String endereco;
    private String telefone;
    private String pessoaContato;
    private String tipoColecao;

    public String getNomeColecao(){
        return this.nomeColecao;
    }

    public void setNomeColecao(String nomeColecao){
        this.nomeColecao = nomeColecao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getPessoaContato(){
        return this.pessoaContato;
    }

    public void setPessoaContato(String pessoaContato){
        this.pessoaContato = pessoaContato;
    }

    public String getTipoColecao(){
        return this.tipoColecao;
    }

    public void setTipoColecao(String tipoColecao){
        this.tipoColecao = tipoColecao;
    }    
}
