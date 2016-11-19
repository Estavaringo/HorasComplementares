/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoContato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoContatoDAO implements DAO<TipoContato>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(TipoContato obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO TIPO_CONTATO (TICO_DESC) VALUES (?)";
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
                    = "DELETE FROM TIPO_CONTATO WHERE TICO_ID = ?";
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
    public void Alterar(TipoContato obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE TIPO_CONTATO SET TICO_DESC = ? WHERE TICO_ID = ?";
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
    public ArrayList<TipoContato> Consultar() throws SQLException {
        try {
            ArrayList<TipoContato> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT TICO_ID, TICO_DESC FROM TIPO_CONTATO");
            while (rs.next()) {
                TipoContato obj = new TipoContato();
                obj.setCodigo(rs.getInt("TICO_ID"));
                obj.setDescricao(rs.getString("TICO_DESC"));
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
    public TipoContato Consultar(int codigo) throws SQLException {
        try {
            TipoContato obj = null;
            bd.conectar();
            String strSQL = "SELECT TICO_ID, TICO_DESC FROM TIPO_CONTATO WHERE TICO_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoContato();
                obj.setCodigo(rs.getInt("TICO_ID"));
                obj.setDescricao(rs.getString("TICO_DESC"));
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
