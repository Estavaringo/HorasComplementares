/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Documento;
import Bean.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class DocumentoDAO implements DAO<Documento>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(Documento obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO DOCUMENTO (DOCU_DESC, DOCU_URL, DOCU_VISI, DOCU_DT, USUA_ID) VALUES (?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getUrl());
            p.setBoolean(3, obj.isVisivel());
            p.setDate(4, obj.getData());
            p.setInt(5, obj.getUsuario().getCodigo());
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
                    = "DELETE FROM DOCUMENTO WHERE DOCU_ID = ?";
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
    public void Alterar(Documento obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE DOCUMENTO SET DOCU_DESC = ?, DOCU_URL = ?, DOCU_VISI = ?, DOCU_DT = ?, USUA_ID = ? WHERE DOCU_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getUrl());
            p.setBoolean(3, obj.isVisivel());
            p.setDate(4, obj.getData());
            p.setInt(5, obj.getUsuario().getCodigo());
            p.setInt(6, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<Documento> Consultar() throws SQLException {
        try {
            ArrayList<Documento> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT DOCU_ID, DOCU_DESC, DOCU_URL, DOCU_VISI, DOCU_DT, USUA_ID FROM DOCUMENTO");
            while (rs.next()) {
                Documento obj = new Documento();
                Usuario usuario = new Usuario();
                
                obj.setCodigo(rs.getInt("DOCU_ID"));
                obj.setDescricao(rs.getString("DOCU_DESC"));
                obj.setUrl(rs.getString("DOCU_URL"));
                obj.setVisivel(rs.getBoolean("DOCU_VISI"));
                obj.setData(rs.getDate("DOCU_DT"));
                
                usuario = new UsuarioDAO().Consultar(rs.getString("USUA_ID"));
                        
                obj.setUsuario(usuario);
                
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
    public Documento Consultar(int codigo) throws SQLException {
        try {
            Documento obj = null;
            bd.conectar();
            String strSQL = "SELECT DOCU_ID, DOCU_DESC, DOCU_URL, DOCU_VISI, DOCU_DT, USUA_ID FROM DOCUMENTO WHERE DOCU_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Documento();
                Usuario usuario = new Usuario();
                
                obj.setCodigo(rs.getInt("DOCU_ID"));
                obj.setDescricao(rs.getString("DOCU_DESC"));
                obj.setUrl(rs.getString("DOCU_URL"));
                obj.setVisivel(rs.getBoolean("DOCU_VISI"));
                obj.setData(rs.getDate("DOCU_DT"));
                
                usuario = new UsuarioDAO().Consultar(rs.getString("USUA_ID"));
                
                obj.setUsuario(usuario);
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
