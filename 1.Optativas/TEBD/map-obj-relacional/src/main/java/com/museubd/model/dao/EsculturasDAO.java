package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Esculturas;

public class EsculturasDAO {
    public static void insert(Esculturas escultura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO ESCULTURAS "
                + "(num_obj2, material, altura, peso) " 
                + "VALUES(?, ?, ?, ?)");
        
            stmt.setInt(1, escultura.getNum_obj2());
            stmt.setString(2, escultura.getMaterial());
            stmt.setInt(3, escultura.getAltura());
            stmt.setInt(4, escultura.getPeso());
            stmt.executeUpdate();
            System.out.println("Escultura inserida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert EsculturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Esculturas escultura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM ESCULTURAS WHERE num_obj2 = ?");
            stmt.setInt(1, escultura.getNum_obj2());
            stmt.executeUpdate();
            System.out.println("Escultura excluida com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete EsculturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Esculturas escultura){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE ESCULTURAS SET " 
                + "material = ?, altura = ?, peso = ?" 
                + "WHERE num_obj2 = ?");
            stmt.setString(1, escultura.getMaterial());
            stmt.setInt(2, escultura.getAltura());
            stmt.setInt(3, escultura.getPeso());
            stmt.setInt(4, escultura.getNum_obj2());
            stmt.executeUpdate();
            System.out.println("Escultura atualizada com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update EsculturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Esculturas> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Esculturas> esculturasList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM ESCULTURAS");
            rs = stmt.executeQuery();

            while (rs.next()){
                Esculturas escultura = new Esculturas();
                escultura.setNum_obj2(rs.getInt("num_obj2"));
                escultura.setMaterial(rs.getString("material"));
                escultura.setAltura(rs.getInt("altura"));
                escultura.setPeso(rs.getInt("peso"));
                esculturasList.add(escultura);
            }

        }catch (SQLException e){
            System.out.println("Exception read EsculturasDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return esculturasList;
    }
}
