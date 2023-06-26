package com.museubd.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionBD{
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String dbname = "postgres";
    private static final String user = "postgres";
    private static final String password = "gato44";

    public static Connection openConnection(){
        try{
            Connection con = DriverManager.getConnection(URL+dbname, user, password);
            System.out.println("Connection Established");
            return con;
        }catch (SQLException e){
            throw new RuntimeException("Exception openConnection");
        }
    }


    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
                System.out.println("Connection Closed");
            }
        }catch (SQLException e){
            System.out.println("Exception closeConnection1");
        }
    }


    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch (SQLException e){
            System.out.println("Exception closeConnection2");
        }
    }


    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try{
            if(rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            System.out.println("Exception closeConnection3");
        }
    }

}