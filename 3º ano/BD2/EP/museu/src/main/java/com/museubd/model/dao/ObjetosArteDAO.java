package com.museubd.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.museubd.connection.ConnectionBD;
import com.museubd.model.bean.ObjetosArte;

public class ObjetosArteDAO{

    public static void insert(ObjetosArte objetoArte){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "INSERT INTO OBJETOS_ARTE "
                + "(num_id, titulo, nome_artista, descricao, ano_criacao, periodo_art, pais_cultura, estilo, tipo, status, custo) " 
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
            stmt.setInt(1, objetoArte.getNumID());
            stmt.setString(2, objetoArte.getTitulo());
            stmt.setString(3, objetoArte.getNomeArtista());
            stmt.setString(4, objetoArte.getDescricao());
            stmt.setDate(5, objetoArte.getAnoCriacao());
            stmt.setString(6, objetoArte.getPeriodoArt());
            stmt.setString(7, objetoArte.getPaisCultura());
            stmt.setString(8, objetoArte.getEstilo());
            stmt.setString(9, objetoArte.getTipo());
            stmt.setString(10, objetoArte.getStatus());  
            stmt.setDouble(11, objetoArte.getCusto());
            stmt.executeUpdate();
            System.out.println("ObjetoArte inserido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception insert ObjetosArteDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }
    }

    public static void delete(ObjetosArte objetoArte){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("DELETE FROM OBJETOS_ARTE WHERE num_id = ?");
            stmt.setInt(1, objetoArte.getNumID());
            stmt.executeUpdate();
            System.out.println("ObjetoArte excluido com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception delete ObjetosArteDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static void update(ObjetosArte objetoArte){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(
                "UPDATE OBJETOS_ARTE SET " 
                + "titulo = ?, nome_artista = ?, descricao = ?, ano_criacao = ?, periodo_art = ?, pais_cultura = ?, estilo = ?, tipo = ?,  status = ?, custo = ?" 
                + "WHERE num_id = ?");
            stmt.setString(1, objetoArte.getTitulo());
            stmt.setString(2, objetoArte.getNomeArtista());
            stmt.setString(3, objetoArte.getDescricao());
            stmt.setDate(4, objetoArte.getAnoCriacao());
            stmt.setString(5, objetoArte.getPeriodoArt());
            stmt.setString(6, objetoArte.getPaisCultura());
            stmt.setString(7, objetoArte.getEstilo());
            stmt.setString(8, objetoArte.getTipo());
            stmt.setString(9, objetoArte.getStatus());
            stmt.setDouble(10, objetoArte.getCusto());
            stmt.setInt(11, objetoArte.getNumID());
            stmt.executeUpdate();
            System.out.println("ObjetoArte atualizado com sucesso!");

        }catch (SQLException e){
            System.out.println("Exception update ObjetosArteDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt);
        }

    }

    public static List<ObjetosArte> read(){

        Connection con = ConnectionBD.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ObjetosArte> objetosArteList = new ArrayList<>();

        try{
            stmt = con.prepareStatement("SELECT * FROM OBJETOS_ARTE");
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
            System.out.println("Exception read ObjetosArteDAO");
        }finally{
            ConnectionBD.closeConnection(con, stmt, rs);
        }
        return objetosArteList;
    }

}
