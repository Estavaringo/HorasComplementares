/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.StatusNoticia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class StatusNoticiaDAO implements DAO<StatusNoticia>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(StatusNoticia obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO STATUS_NOTICIA (STNO_DESC) VALUES (?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }    }

    @Override
    public void Excluir(int codigo) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "DELETE FROM STATUS_NOTICIA WHERE STNO_ID = ?";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setInt(1, codigo);
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public void Alterar(StatusNoticia obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE STATUS_NOTICIA SET STNO_DESC = ? WHERE STNO_ID = ?";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<StatusNoticia> Consultar() throws SQLException {
        try {
            ArrayList<StatusNoticia> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT STNO_ID, STNO_DESC FROM STATUS_NOTICIA");
            while (rs.next()) {
                StatusNoticia obj = new StatusNoticia();
                obj.setCodigo(rs.getInt("STNO_ID"));
                obj.setDescricao(rs.getString("STNO_DESC"));
                lista.add(obj);
            }
            comando.close();
            bd.desconectar();
            return lista;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public StatusNoticia Consultar(int codigo) throws SQLException {
        try {
            StatusNoticia obj = null;
            bd.conectar();
            String strSQL = "SELECT STNO_ID, STNO_DESC FROM STATUS_NOTICIA WHERE STNO_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new StatusNoticia();
                obj.setCodigo(rs.getInt("STNO_ID"));
                obj.setDescricao(rs.getString("STNO_DESC"));
                p.close();
                bd.desconectar();
                return obj;
            }
            p.close();
            bd.desconectar();
            return obj;
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }
    
}
