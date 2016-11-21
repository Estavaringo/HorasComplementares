/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.TipoUsuario;
import Bean.StatusNoticia;
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
public class UsuarioDAO implements DAO<Usuario>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(Usuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO USUARIO (USUA_NM, USUA_PRON, USUA_FUNC, USUA_DT_INI, USUA_SEME, USUA_LOGIN, USUA_SENHA, CURS_ID, TIUS_ID, USUA_ATIVO) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getNome());
            p.setString(2, obj.getProntuario());
            p.setString(3, obj.getFuncional());
            p.setDate(4, obj.getDataMatricula());
            p.setString(5, obj.getSemestre());
            p.setString(6, obj.getLogin());
            p.setString(7, obj.getSenha());
            p.setInt(8, obj.getCurso().getCodigo());
            p.setInt(9, obj.getTipoUsuario().getCodigo());
            p.setBoolean(10, obj.isAtivo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public void Excluir(int codigo) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "DELETE FROM USUARIO WHERE USUA_ID = ?";
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
    public void Alterar(Usuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE USUARIO SET USUA_NM = ?, USUA_PRON = ?, USUA_FUNC = ?, USUA_DT_INI = ?, USUA_SEME = ?, USUA_LOGIN = ?, USUA_SENHA = ?, CURS_ID = ?, TIUS_ID = ?, USUA_ATIVO = ? WHERE USUA_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getNome());
            p.setString(2, obj.getProntuario());
            p.setString(3, obj.getFuncional());
            p.setDate(4, obj.getDataMatricula());
            p.setString(5, obj.getSemestre());
            p.setString(6, obj.getLogin());
            p.setString(7, obj.getSenha());
            p.setInt(8, obj.getCurso().getCodigo());
            p.setInt(9, obj.getTipoUsuario().getCodigo());
            p.setBoolean(10, obj.isAtivo());
            p.setInt(11, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<Usuario> Consultar() throws SQLException {
        try {
            ArrayList<Usuario> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.USUA_LOGIN, U.USUA_SENHA, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "C.CURS_ID, C.CURS_DESC, C.CURS_HR_NECE, "
                    + "T.TIUS_ID, T.TIUS_DESC, "
                    + "FROM USUARIO U "
                    + "INNER JOIN CURSO C "
                    + "ON U.CURS_ID = C.CURS_ID "
                    + "INNER JOIN TIPO_USUARIO T "
                    + "ON U.TIUS_ID = T.TIUS_ID ");
            while (rs.next()) {
                Usuario obj = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();

                obj.setCodigo(rs.getInt("N.USUA_ID"));
                obj.setNome(rs.getString("N.USUA_NM"));
                obj.setProntuario(rs.getString("N.USUA_PRON"));
                obj.setFuncional(rs.getString("N.USUA_FUNC"));
                obj.setDataMatricula(rs.getDate("N.USUA_DT_INI"));
                obj.setSemestre(rs.getString("N.USUA_SEME"));
                obj.setLogin(rs.getString("N.USUA_LOGIN"));
                obj.setSenha(rs.getString("N.USUA_SENHA"));
                obj.setAtivo(rs.getBoolean("N.USUA_ATIVO"));

                curso.setCodigo(rs.getInt("C.USUA_ID"));
                curso.setDescricao(rs.getString("C.CURS_DESC"));
                curso.setCargaHoraria(rs.getInt("C.CURS_HR_NECE"));
                obj.setCurso(curso);

                tipoUsuario.setCodigo(rs.getInt("T.TIUS_ID"));
                tipoUsuario.setDescricao(rs.getString("T.TIUS_DESC"));
                obj.setTipoUsuario(tipoUsuario);

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
    public Usuario Consultar(int codigo) throws SQLException {
        try {
            Usuario obj = null;
            bd.conectar();
            String strSQL = "SELECT U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.USUA_LOGIN, U.USUA_SENHA, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "C.CURS_ID, C.CURS_DESC, C.CURS_HR_NECE, "
                    + "T.TIUS_ID, T.TIUS_DESC, "
                    + "FROM USUARIO U "
                    + "INNER JOIN CURSO C "
                    + "ON U.CURS_ID = C.CURS_ID "
                    + "INNER JOIN TIPO_USUARIO T "
                    + "ON U.TIUS_ID = T.TIUS_ID "
                    + "WHERE U.USUA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();

                obj.setCodigo(rs.getInt("N.USUA_ID"));
                obj.setNome(rs.getString("N.USUA_NM"));
                obj.setProntuario(rs.getString("N.USUA_PRON"));
                obj.setFuncional(rs.getString("N.USUA_FUNC"));
                obj.setDataMatricula(rs.getDate("N.USUA_DT_INI"));
                obj.setSemestre(rs.getString("N.USUA_SEME"));
                obj.setLogin(rs.getString("N.USUA_LOGIN"));
                obj.setSenha(rs.getString("N.USUA_SENHA"));
                obj.setAtivo(rs.getBoolean("N.USUA_ATIVO"));

                curso.setCodigo(rs.getInt("C.USUA_ID"));
                curso.setDescricao(rs.getString("C.CURS_DESC"));
                curso.setCargaHoraria(rs.getInt("C.CURS_HR_NECE"));
                obj.setCurso(curso);

                tipoUsuario.setCodigo(rs.getInt("T.TIUS_ID"));
                tipoUsuario.setDescricao(rs.getString("T.TIUS_DESC"));
                obj.setTipoUsuario(tipoUsuario);

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
