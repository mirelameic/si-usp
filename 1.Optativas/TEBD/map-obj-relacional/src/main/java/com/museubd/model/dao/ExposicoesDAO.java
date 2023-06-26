package com.museubd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Exposicoes;

public class ExposicoesDAO{

    public static void insert(Exposicoes exposicao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO EXPOSICOES "
                + "(nome_exposicao, data_inicio, data_final) " 
                + "VALUES(?, ?, ?)");
        
            stmt.setString(1, exposicao.getNomeExposicao());
            stmt.setDate(2, exposicao.getDataInicio());
            stmt.setDate(3, exposicao.getDataFinal());
            stmt.executeUpdate();
            System.out.println("Exposicao inserida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert ExposicoesDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }


    public static void delete(Exposicoes exposicao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM EXPOSICOES WHERE nome_exposicao = ?");
            stmt.setString(1, exposicao.getNomeExposicao());
            stmt.executeUpdate();
            System.out.println("Exposicao excluida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete ExposicoesDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Exposicoes exposicao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE EXPOSICOES SET " 
                + "data_inicio = ?, data_final = ?" 
                + "WHERE nome_exposicao = ?");
            stmt.setDate(1, exposicao.getDataInicio());
            stmt.setDate(2, exposicao.getDataFinal());
            stmt.setString(3, exposicao.getNomeExposicao());
            stmt.executeUpdate();
            System.out.println("Exposicao atualizada com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update ExposicoesDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Exposicoes> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Exposicoes> exposicoesList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM EXPOSICOES");
            rs = stmt.executeQuery();

            while (rs.next()){
                Exposicoes exposicao = new Exposicoes();
                exposicao.setNomeExposicao(rs.getString("nome_exposicao"));
                exposicao.setDataInicio(rs.getDate("data_inicio"));
                exposicao.setDataFinal(rs.getDate("data_final"));
                exposicoesList.add(exposicao);
            }

        }catch (SQLException e){
            System.out.println("Exception read ExposicaoDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return exposicoesList;
    }
    
}
