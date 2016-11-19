/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoDocumento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoDocumentoDAO implements DAO<TipoDocumento>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(TipoDocumento obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO TIPO_DOCUMENTO (TIDO_DESC) VALUES (?)";
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
                    = "DELETE FROM TIPO_DOCUMENTO WHERE TIDO_ID = ?";
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
    public void Alterar(TipoDocumento obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE TIPO_DOCUMENTO SET TIDO_DESC = ? WHERE TIDO_ID = ?";
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
    public ArrayList<TipoDocumento> Consultar() throws SQLException {
        try {
            ArrayList<TipoDocumento> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT TIDO_ID, TIDO_DESC FROM TIPO_DOCUMENTO");
            while (rs.next()) {
                TipoDocumento obj = new TipoDocumento();
                obj.setCodigo(rs.getInt("TIDO_ID"));
                obj.setDescricao(rs.getString("TIDO_DESC"));
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
    public TipoDocumento Consultar(int codigo) throws SQLException {
        try {
            TipoDocumento obj = null;
            bd.conectar();
            String strSQL = "SELECT TIDO_ID, TIDO_DESC FROM TIPO_DOCUMENTO WHERE TIDO_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoDocumento();
                obj.setCodigo(rs.getInt("TIDO_ID"));
                obj.setDescricao(rs.getString("TIDO_DESC"));
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
