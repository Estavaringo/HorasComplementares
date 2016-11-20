/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.StatusRelatorioAtividade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class StatusRelatorioAtividadeDAO implements DAO<StatusRelatorioAtividade>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(StatusRelatorioAtividade obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO STATUS_RELATORIO_ATIVIDADE (STRA_DESC) VALUES (?)";
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
                    = "DELETE FROM STATUS_RELATORIO_ATIVIDADE WHERE STRA_ID = ?";
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
    public void Alterar(StatusRelatorioAtividade obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE STATUS_RELATORIO_ATIVIDADE SET STRA_DESC = ? WHERE STRA_ID = ?";
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
    public ArrayList<StatusRelatorioAtividade> Consultar() throws SQLException {
        try {
            ArrayList<StatusRelatorioAtividade> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT STRA_ID, STRA_DESC FROM STATUS_RELATORIO_ATIVIDADE");
            while (rs.next()) {
                StatusRelatorioAtividade obj = new StatusRelatorioAtividade();
                obj.setCodigo(rs.getInt("STRA_ID"));
                obj.setDescricao(rs.getString("STRA_DESC"));
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
    public StatusRelatorioAtividade Consultar(int codigo) throws SQLException {
        try {
            StatusRelatorioAtividade obj = null;
            bd.conectar();
            String strSQL = "SELECT STRA_ID, STRA_DESC FROM STATUS_RELATORIO_ATIVIDADE WHERE STRA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new StatusRelatorioAtividade();
                obj.setCodigo(rs.getInt("STRA_ID"));
                obj.setDescricao(rs.getString("STRA_DESC"));
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
