package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Pinturas;

public class PinturasDAO {
    public static void insert(Pinturas pintura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO PINTURAS "
                + "(num_obj1, tipo_tinta, suporte)" 
                + "VALUES(?, ?, ?)");
        
            stmt.setInt(1, pintura.getNum_obj1());
            stmt.setString(2, pintura.getTipoTinta());
            stmt.setString(3, pintura.getSuporte());
            stmt.executeUpdate();
            System.out.println("Pintura inserida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert PinturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Pinturas pintura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM PINTURAS WHERE num_obj1 = ?");
            stmt.setInt(1, pintura.getNum_obj1());
            stmt.executeUpdate();
            System.out.println("Pintura excluida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete PinturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Pinturas pintura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE PINTURAS SET " 
                + "tipo_tinta = ?, suporte = ?" 
                + "WHERE num_obj1 = ?");
            stmt.setString(1, pintura.getTipoTinta());
            stmt.setString(2, pintura.getSuporte());
            stmt.setInt(3, pintura.getNum_obj1());
            stmt.executeUpdate();
            System.out.println("Pintura atualizada com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update PinturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Pinturas> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pinturas> pinturasList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM PINTURAS");
            rs = stmt.executeQuery();

            while (rs.next()){
                Pinturas pintura = new Pinturas();
                pintura.setNum_obj1(rs.getInt("num_obj1"));
                pintura.setTipoTinta(rs.getString("tipo_tinta"));
                pintura.setSuporte(rs.getString("suporte"));
                pinturasList.add(pintura);
            }

        }catch (SQLException e){
            System.out.println("Exception read PinturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return pinturasList;
    }
}
