/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ContatoUsuario;
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
public class ContatoUsuarioDAO implements DAO<ContatoUsuario>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(ContatoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO CONTATO_USUARIO (CONT_USUA_DESC, CONT_USUA_URL, CONT_USUA_VISI, CONT_USUA_DT, USUA_ID) VALUES (?,?,?,?,?)";
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
                    = "DELETE FROM CONTATO_USUARIO WHERE CONT_USUA_ID = ?";
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
    public void Alterar(ContatoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE CONTATO_USUARIO SET CONT_USUA_DESC = ?, CONT_USUA_URL = ?, CONT_USUA_VISI = ?, CONT_USUA_DT = ?, USUA_ID = ? WHERE CONT_USUA_ID = ? ";
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
    public ArrayList<ContatoUsuario> Consultar() throws SQLException {
        try {
            ArrayList<ContatoUsuario> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT CONT_USUA_ID, CONT_USUA_DESC, CONT_USUA_URL, CONT_USUA_VISI, CONT_USUA_DT, USUA_ID FROM CONTATO_USUARIO");
            while (rs.next()) {
                ContatoUsuario obj = new ContatoUsuario();
                Usuario usuario = new Usuario();
                
                obj.setCodigo(rs.getInt("CONT_USUA_ID"));
                obj.setDescricao(rs.getString("CONT_USUA_DESC"));
                obj.setUrl(rs.getString("CONT_USUA_URL"));
                obj.setVisivel(rs.getBoolean("CONT_USUA_VISI"));
                obj.setData(rs.getDate("CONT_USUA_DT"));
                
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
    public ContatoUsuario Consultar(int codigo) throws SQLException {
        try {
            ContatoUsuario obj = null;
            bd.conectar();
            String strSQL = "SELECT CONT_USUA_ID, CONT_USUA_DESC, CONT_USUA_URL, CONT_USUA_VISI, CONT_USUA_DT, USUA_ID FROM CONTATO_USUARIO WHERE CONT_USUA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new ContatoUsuario();
                Usuario usuario = new Usuario();
                
                obj.setCodigo(rs.getInt("CONT_USUA_ID"));
                obj.setDescricao(rs.getString("CONT_USUA_DESC"));
                obj.setUrl(rs.getString("CONT_USUA_URL"));
                obj.setVisivel(rs.getBoolean("CONT_USUA_VISI"));
                obj.setData(rs.getDate("CONT_USUA_DT"));
                
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
