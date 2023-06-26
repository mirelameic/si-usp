package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Artista;

public class ArtistaDAO{

    public static void insert(Artista artista){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO ARTISTA "
                + "(nome, descricao, estilo_principal, periodo_art, pais_origem, data_nascimento, data_morte) " 
                + "VALUES(?, ?, ?, ?, ?, ?, ?)");
        
            stmt.setString(1, artista.getNome());
            stmt.setString(2, artista.getDescricao());
            stmt.setString(3, artista.getEstiloPrincipal());
            stmt.setString(4, artista.getPeriodoArt());
            stmt.setString(5, artista.getPaisOrig());
            stmt.setDate(6, artista.getDataNasc());
            stmt.setDate(7, artista.getDataMorte());
            stmt.executeUpdate();
            System.out.println("Artista inserido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert ArtistaDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Artista artista){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM ARTISTA WHERE nome = ?");
            stmt.setString(1, artista.getNome());
            stmt.executeUpdate();
            System.out.println("Artista excluido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete ArtistaDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Artista artista){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE ARTISTA SET " 
                + "descricao = ?, estilo_principal = ?, periodo_art = ?, pais_origem = ?, data_nascimento = ?, data_morte = ? " 
                + "WHERE nome = ?");
            stmt.setString(1, artista.getDescricao());
            stmt.setString(2, artista.getEstiloPrincipal());
            stmt.setString(3, artista.getPeriodoArt());
            stmt.setString(4, artista.getPaisOrig());
            stmt.setDate(5, artista.getDataNasc());
            stmt.setDate(6, artista.getDataMorte());
            stmt.setString(7, artista.getNome());
            stmt.executeUpdate();
            System.out.println("Artista atualizado com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update ArtistaDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

      
    public static List<Artista> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Artista> artistasList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM ARTISTA");
            rs = stmt.executeQuery();

            while (rs.next()){
                Artista artista = new Artista();
                artista.setNome(rs.getString("nome"));
                artista.setDescricao(rs.getString("descricao"));
                artista.setEstiloPrincipal(rs.getString("estilo_principal"));
                artista.setPeriodoArt(rs.getString("periodo_art"));
                artista.setPaisOrig(rs.getString("pais_origem"));
                artista.setDataNasc(rs.getDate("data_nascimento"));
                artista.setDataMorte(rs.getDate("data_morte"));
                artistasList.add(artista);
            }

        }catch (SQLException e){
            System.out.println("Exception read ArtistaDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return artistasList;
    }
}
