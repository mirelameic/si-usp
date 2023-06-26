package com.museubd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Colecao;

public class ColecaoDAO{

    public static void insert(Colecao colecao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO COLECAO "
                + "(nome_colecao, descricao, endereco, telefone, pessoa_contato, tipo_colecao) " 
                + "VALUES(?, ?, ?, ?, ?, ?)");
        
            stmt.setString(1, colecao.getNomeColecao());
            stmt.setString(2, colecao.getDescricao());
            stmt.setString(3, colecao.getEndereco());
            stmt.setString(4, colecao.getTelefone());
            stmt.setString(5, colecao.getPessoaContato());
            stmt.setString(6, colecao.getTipoColecao());
            stmt.executeUpdate();
            System.out.println("Colecao inserida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert ColecaoDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Colecao colecao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM COLECAO WHERE nome_colecao = ?");
            stmt.setString(1, colecao.getNomeColecao());
            stmt.executeUpdate();
            System.out.println("Colecao excluida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete ColecaoDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Colecao colecao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE COLECAO SET " 
                + "descricao = ?, endereco = ?, telefone = ?, pessoa_contato = ?, tipo_colecao = ?" 
                + "WHERE nome_colecao = ?");
            stmt.setString(1, colecao.getDescricao());
            stmt.setString(2, colecao.getEndereco());
            stmt.setString(3, colecao.getTelefone());
            stmt.setString(4, colecao.getPessoaContato());
            stmt.setString(5, colecao.getTipoColecao());
            stmt.setString(6, colecao.getNomeColecao());
            stmt.executeUpdate();
            System.out.println("Colecao atualizada com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update ColecaoDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Colecao> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Colecao> colecaoList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM COLECAO");
            rs = stmt.executeQuery();

            while (rs.next()){
                Colecao colecao = new Colecao();
                colecao.setNomeColecao(rs.getString("nome_colecao"));
                colecao.setDescricao(rs.getString("descricao"));
                colecao.setEndereco(rs.getString("endereco"));
                colecao.setTelefone(rs.getString("telefone"));
                colecao.setPessoaContato(rs.getString("pessoa_contato"));
                colecao.setTipoColecao(rs.getString("tipo_colecao"));
                colecaoList.add(colecao);
            }

        }catch (SQLException e){
            System.out.println("Exception read ColecaoDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return colecaoList;
    }
    
}
