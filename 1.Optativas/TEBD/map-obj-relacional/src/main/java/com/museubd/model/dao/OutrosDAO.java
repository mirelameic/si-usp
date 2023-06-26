package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Outros;

public class OutrosDAO {
    public static void insert(Outros outro){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO OUTROS "
                + "(num_obj3, tipo) " 
                + "VALUES(?, ?)");
        
            stmt.setInt(1, outro.getNum_obj3());
            stmt.setString(2, outro.getTipo());
            stmt.executeUpdate();
            System.out.println("Outro inserido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update OutrosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Outros outro){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM OUTROS WHERE num_obj3 = ?");
            stmt.setInt(1, outro.getNum_obj3());
            stmt.executeUpdate();
            System.out.println("Outro excluido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete OutrosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Outros outro){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE OUTROS SET " 
                + "tipo = ?" 
                + "WHERE num_obj3 = ?");
            stmt.setString(1, outro.getTipo());
            stmt.setInt(2, outro.getNum_obj3());
            stmt.executeUpdate();
            System.out.println("Outro atualizada com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update OutrosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Outros> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Outros> outrosList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM OUTROS");
            rs = stmt.executeQuery();

            while (rs.next()){
                Outros outro = new Outros();
                outro.setNum_obj3(rs.getInt("num_obj3"));
                outro.setTipo(rs.getString("tipo"));
                outrosList.add(outro);
            }

        }catch (SQLException e){
            System.out.println("Exception read OutrosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return outrosList;
    }
}
