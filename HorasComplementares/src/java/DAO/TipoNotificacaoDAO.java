/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoNotificacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoNotificacaoDAO implements DAO<TipoNotificacao>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(TipoNotificacao obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO TIPO_NOTIFICACAO (TINO_DESC) VALUES (?)";
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
                    = "DELETE FROM TIPO_NOTIFICACAO WHERE TINO_ID = ?";
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
    public void Alterar(TipoNotificacao obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE TIPO_NOTIFICACAO SET TINO_DESC = ? WHERE TINO_ID = ?";
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
    public ArrayList<TipoNotificacao> Consultar() throws SQLException {
        try {
            ArrayList<TipoNotificacao> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT TINO_ID, TINO_DESC FROM TIPO_NOTIFICACAO");
            while (rs.next()) {
                TipoNotificacao obj = new TipoNotificacao();
                obj.setCodigo(rs.getInt("TINO_ID"));
                obj.setDescricao(rs.getString("TINO_DESC"));
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
    public TipoNotificacao Consultar(int codigo) throws SQLException {
        try {
            TipoNotificacao obj = null;
            bd.conectar();
            String strSQL = "SELECT TINO_ID, TINO_DESC FROM TIPO_NOTIFICACAO WHERE TINO_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoNotificacao();
                obj.setCodigo(rs.getInt("TINO_ID"));
                obj.setDescricao(rs.getString("TINO_DESC"));
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
