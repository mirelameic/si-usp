package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Permanentes;

public class PermanentesDAO {
    public static void insert(Permanentes permanente){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO PERMANENTES "
                + "(num_obj5, em_exposicao, data_aquisicao) " 
                + "VALUES(?, ?::bit, ?)");
        
            stmt.setInt(1, permanente.getNum_obj5());
            stmt.setString(2, permanente.getEmExposicao());
            stmt.setDate(3, permanente.getDataAquisicao());
            stmt.executeUpdate();
            System.out.println("Permanente inserido com sucesso!");

        }catch (SQLException e){
            System.out.println(e);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Permanentes permanente){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM PERMANENTES WHERE num_obj5 = ?");
            stmt.setInt(1, permanente.getNum_obj5());
            stmt.executeUpdate();
            System.out.println("Permanente excluido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete PermanentesDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Permanentes permanente){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE PERMANENTES SET " 
                + "em_exposicao = ?::bit, data_aquisicao = ?" 
                + "WHERE num_obj5 = ?");
            stmt.setString(1, permanente.getEmExposicao());
            stmt.setDate(2, permanente.getDataAquisicao());
            stmt.setInt(3, permanente.getNum_obj5());
            stmt.executeUpdate();
            System.out.println("Permanente atualizado com sucesso!");

        }catch (SQLException e){
            System.out.println(e);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Permanentes> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Permanentes> permanentesList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM PERMANENTES");
            rs = stmt.executeQuery();

            while (rs.next()){
                Permanentes permanente = new Permanentes();
                permanente.setNum_obj5(rs.getInt("num_obj5"));
                permanente.setEmExposicao(rs.getString("em_exposicao"));
                permanente.setDataAquisicao(rs.getDate("data_aquisicao"));
                permanentesList.add(permanente);
            }

        }catch (SQLException e){
            System.out.println("Exception read PermanentesDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return permanentesList;
    }
}
