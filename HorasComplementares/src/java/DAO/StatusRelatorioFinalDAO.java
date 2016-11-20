/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.StatusRelatorioFinal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class StatusRelatorioFinalDAO implements DAO<StatusRelatorioFinal>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(StatusRelatorioFinal obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO STATUS_RELATORIO_FINAL (STRF_DESC) VALUES (?)";
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
                    = "DELETE FROM STATUS_RELATORIO_FINAL WHERE STRF_ID = ?";
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
    public void Alterar(StatusRelatorioFinal obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE STATUS_RELATORIO_FINAL SET STRF_DESC = ? WHERE STRF_ID = ?";
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
    public ArrayList<StatusRelatorioFinal> Consultar() throws SQLException {
        try {
            ArrayList<StatusRelatorioFinal> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT STRF_ID, STRF_DESC FROM STATUS_RELATORIO_FINAL");
            while (rs.next()) {
                StatusRelatorioFinal obj = new StatusRelatorioFinal();
                obj.setCodigo(rs.getInt("STRF_ID"));
                obj.setDescricao(rs.getString("STRF_DESC"));
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
    public StatusRelatorioFinal Consultar(int codigo) throws SQLException {
        try {
            StatusRelatorioFinal obj = null;
            bd.conectar();
            String strSQL = "SELECT STRF_ID, STRF_DESC FROM STATUS_RELATORIO_FINAL WHERE STRF_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new StatusRelatorioFinal();
                obj.setCodigo(rs.getInt("STRF_ID"));
                obj.setDescricao(rs.getString("STRF_DESC"));
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
