package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.Emprestados;

public class EmprestadosDAO {
    public static void insert(Emprestados emprestado){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO EMPRESTADOS "
                + "(num_obj4, nome_colecao, data_emprestimo, data_devolucao) " 
                + "VALUES(?, ?, ?, ?)");
        
            stmt.setInt(1, emprestado.getNum_obj4());
            stmt.setString(2, emprestado.getNomeColecao());
            stmt.setDate(3, emprestado.getDataEmprestimo());
            stmt.setDate(4, emprestado.getDataDevolucao());
            stmt.executeUpdate();
            System.out.println("Emprestado inserido com sucesso!");

        }catch (SQLException e){
            System.out.println(e);
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(Emprestados emprestado){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM EMPRESTADOS WHERE num_obj4 = ?");
            stmt.setInt(1, emprestado.getNum_obj4());
            stmt.executeUpdate();
            System.out.println("Emprestado excluido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete EmprestadosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(Emprestados emprestado){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE EMPRESTADOS SET " 
                + "nome_colecao = ?, data_emprestimo = ?, data_devolucao = ?" 
                + "WHERE num_obj4 = ?");
            stmt.setString(1, emprestado.getNomeColecao());
            stmt.setDate(2, emprestado.getDataEmprestimo());
            stmt.setDate(3, emprestado.getDataDevolucao());
            stmt.setInt(4, emprestado.getNum_obj4());
            stmt.executeUpdate();
            System.out.println("Emprestado atualizado com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update EmprestadosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<Emprestados> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Emprestados> emprestadosList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM EMPRESTADOS");
            rs = stmt.executeQuery();

            while (rs.next()){
                Emprestados emprestado = new Emprestados();
                emprestado.setNum_obj4(rs.getInt("num_obj4"));
                emprestado.setNomeColecao(rs.getString("nome_colecao"));
                emprestado.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestado.setDataDevolucao(rs.getDate("data_devolucao"));
                emprestadosList.add(emprestado);
            }

        }catch (SQLException e){
            System.out.println("Exception read EmprestadosDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return emprestadosList;
    }
}
