/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoUsuarioDAO implements DAO<TipoUsuario>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(TipoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO TIPO_USUARIO (TIUS_DESC) VALUES (?)";
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
                    = "DELETE FROM TIPO_USUARIO WHERE TIUS_ID = ?";
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
    public void Alterar(TipoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE TIPO_USUARIO SET TIUS_DESC = ? WHERE TIUS_ID = ?";
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
    public ArrayList<TipoUsuario> Consultar() throws SQLException {
        try {
            ArrayList<TipoUsuario> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT TIUS_ID, TIUS_DESC FROM TIPO_USUARIO");
            while (rs.next()) {
                TipoUsuario obj = new TipoUsuario();
                obj.setCodigo(rs.getInt("TIUS_ID"));
                obj.setDescricao(rs.getString("TIUS_DESC"));
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
    public TipoUsuario Consultar(int codigo) throws SQLException {
        try {
            TipoUsuario obj = null;
            bd.conectar();
            String strSQL = "SELECT TIUS_ID, TIUS_DESC FROM TIPO_USUARIO WHERE TIUS_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoUsuario();
                obj.setCodigo(rs.getInt("TIUS_ID"));
                obj.setDescricao(rs.getString("TIUS_DESC"));
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
