/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ContatoUsuario;
import Bean.Curso;
import Bean.TipoContato;
import Bean.TipoUsuario;
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
                    = "INSERT INTO CONTATO_USUARIO (CONT_USUA_DESC, TICO_ID, USUA_ID) VALUES (?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getTipoContato().getCodigo());
            p.setInt(3, obj.getUsuario().getCodigo());
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
                    = "UPDATE CONTATO_USUARIO SET CONT_USUA_DESC = ?, TICO_ID = ?, USUA_ID = ? WHERE CONT_USUA_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getTipoContato().getCodigo());
            p.setInt(3, obj.getUsuario().getCodigo());
            p.setInt(4, obj.getCodigo());
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
            ResultSet rs = comando.executeQuery("SELECT C.CONT_USUA_ID, C.CONT_USUA_DESC, C.TICO_ID, C.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "T.TICO_ID, T.TICO_DESC "
                    + "FROM CONTATO_USUARIO C "
                    + "INNER JOIN USUARIO U "
                    + "ON C.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_CONTATO T "
                    + "ON C.TICO_ID = T.TICO_ID ");
            while (rs.next()) {
                ContatoUsuario obj = new ContatoUsuario();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                TipoContato tipoContato = new TipoContato();
                
                obj.setCodigo(rs.getInt("C.CONT_USUA_ID"));
                obj.setDescricao(rs.getString("C.CONT_USUA_DESC"));
                
                usuario.setCodigo(rs.getInt("U.USUA_ID"));
                usuario.setNome(rs.getString("U.USUA_NM"));
                usuario.setProntuario(rs.getString("U.USUA_PRON"));
                usuario.setFuncional(rs.getString("U.USUA_FUNC"));
                usuario.setDataMatricula(rs.getDate("U.USUA_DT_INI"));
                usuario.setSemestre(rs.getString("U.USUA_SEME"));
                usuario.setAtivo(rs.getBoolean("U.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("U.CURS_ID"));
                usuario.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("U.TIUS_ID"));
                usuario.setTipoUsuario(tipoUsuario);
                obj.setUsuario(usuario);
                
                tipoContato.setCodigo(rs.getInt("T.TICO_ID"));
                tipoContato.setDescricao(rs.getString("T.TICO_DESC"));
                obj.setTipoContato(tipoContato);
                
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
            String strSQL = "SELECT C.CONT_USUA_ID, C.CONT_USUA_DESC, C.TICO_ID, C.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "T.TICO_ID, T.TICO_DESC "
                    + "FROM CONTATO_USUARIO C "
                    + "INNER JOIN USUARIO U "
                    + "ON C.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_CONTATO T "
                    + "ON C.TICO_ID = T.TICO_ID "
                    + "WHERE C.CONT_USUA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new ContatoUsuario();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                TipoContato tipoContato = new TipoContato();
                
                obj.setCodigo(rs.getInt("C.CONT_USUA_ID"));
                obj.setDescricao(rs.getString("C.CONT_USUA_DESC"));
                
                usuario.setCodigo(rs.getInt("U.USUA_ID"));
                usuario.setNome(rs.getString("U.USUA_NM"));
                usuario.setProntuario(rs.getString("U.USUA_PRON"));
                usuario.setFuncional(rs.getString("U.USUA_FUNC"));
                usuario.setDataMatricula(rs.getDate("U.USUA_DT_INI"));
                usuario.setSemestre(rs.getString("U.USUA_SEME"));
                usuario.setAtivo(rs.getBoolean("U.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("U.CURS_ID"));
                usuario.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("U.TIUS_ID"));
                usuario.setTipoUsuario(tipoUsuario);
                obj.setUsuario(usuario);
                
                tipoContato.setCodigo(rs.getInt("T.TICO_ID"));
                tipoContato.setDescricao(rs.getString("T.TICO_DESC"));
                obj.setTipoContato(tipoContato);
               
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
