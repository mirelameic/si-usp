package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.ObjetosArte;

public class ConsultasDAO{

    public static List<ObjetosArte> readForTipo(String tipo){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ObjetosArte> objetosArteList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM OBJETOS_ARTE WHERE TIPO LIKE ?");
            stmt.setString(1, "%"+tipo+"%");
            rs = stmt.executeQuery();

            while (rs.next()){
                ObjetosArte objetoArte = new ObjetosArte();
                objetoArte.setNumID(rs.getInt("num_id"));
                objetoArte.setTitulo(rs.getString("titulo"));
                objetoArte.setNomeArtista(rs.getString("nome_artista"));
                objetoArte.setDescricao(rs.getString("descricao"));
                objetoArte.setAnoCriacao(rs.getDate("ano_criacao"));
                objetoArte.setPeriodoArt(rs.getString("periodo_art"));
                objetoArte.setPaisCultura(rs.getString("pais_cultura"));
                objetoArte.setEstilo(rs.getString("estilo"));
                objetoArte.setTipo(rs.getString("tipo"));
                objetoArte.setStatus(rs.getString("status"));
                objetoArte.setCusto(rs.getDouble("custo"));
                objetosArteList.add(objetoArte);
            }

        }catch (SQLException e){
            System.out.println("Exception readForTipo");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return objetosArteList;
    }

    public static List<ObjetosArte> readForClasse(String classe){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ObjetosArte> objetosArteList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM OBJETOS_ARTE WHERE STATUS LIKE ?");
            stmt.setString(1, "%"+classe+"%");
            rs = stmt.executeQuery();

            while (rs.next()){
                ObjetosArte objetoArte = new ObjetosArte();
                objetoArte.setNumID(rs.getInt("num_id"));
                objetoArte.setTitulo(rs.getString("titulo"));
                objetoArte.setNomeArtista(rs.getString("nome_artista"));
                objetoArte.setDescricao(rs.getString("descricao"));
                objetoArte.setAnoCriacao(rs.getDate("ano_criacao"));
                objetoArte.setPeriodoArt(rs.getString("periodo_art"));
                objetoArte.setPaisCultura(rs.getString("pais_cultura"));
                objetoArte.setEstilo(rs.getString("estilo"));
                objetoArte.setTipo(rs.getString("tipo"));
                objetoArte.setStatus(rs.getString("status"));
                objetoArte.setCusto(rs.getDouble("custo"));
                objetosArteList.add(objetoArte);
            }

        }catch (SQLException e){
            System.out.println("Exception readForClasse");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return objetosArteList;
    }

    public static List<ObjetosArte> readControleCompras(int mes, int ano){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ObjetosArte> objetosArteList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * "+
            "FROM PERMANENTES AS P " +
            "JOIN OBJETOS_ARTE AS OA ON P.NUM_OBJ5 = OA.NUM_ID " +
            "AND EXTRACT(MONTH FROM P.DATA_AQUISICAO) = ? " +
            "AND EXTRACT(YEAR FROM P.DATA_AQUISICAO) = ?;");
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            rs = stmt.executeQuery();

            while (rs.next()){
                ObjetosArte objetoArte = new ObjetosArte();
                objetoArte.setNumID(rs.getInt("num_id"));
                objetoArte.setTitulo(rs.getString("titulo"));
                objetoArte.setNomeArtista(rs.getString("nome_artista"));
                objetoArte.setDescricao(rs.getString("descricao"));
                objetoArte.setAnoCriacao(rs.getDate("ano_criacao"));
                objetoArte.setPeriodoArt(rs.getString("periodo_art"));
                objetoArte.setPaisCultura(rs.getString("pais_cultura"));
                objetoArte.setEstilo(rs.getString("estilo"));
                objetoArte.setTipo(rs.getString("tipo"));
                objetoArte.setStatus(rs.getString("status"));
                objetoArte.setCusto(rs.getDouble("custo"));
                objetosArteList.add(objetoArte);
            }

        }catch (SQLException e){
            System.out.println("Exception ControleCompras" + e);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return objetosArteList;
    }
    
    public static int readColecao(int mes, int ano, String colecao){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try{
            stmt = con.prepareStatement("SELECT COUNT (*) "+
            "FROM COLECAO AS C " +
            "RIGHT JOIN EMPRESTADOS AS E ON C.NOME_COLECAO = E.NOME_COLECAO " +
            "WHERE C.NOME_COLECAO = ? "+
            "AND EXTRACT(MONTH FROM E.DATA_EMPRESTIMO) = ? " +
            "AND EXTRACT(YEAR FROM E.DATA_EMPRESTIMO) = ?;");
            stmt.setString(1, colecao);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            rs = stmt.executeQuery();

            rs.next();
            count = rs.getInt("count");

        }catch (SQLException e){
            System.out.println("Exception ControleCompras" + e);
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return count;
    }
}
