/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.NotificacaoUsuario;
import Bean.Curso;
import Bean.TipoNotificacao;
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
public class NotificacaoUsuarioDAO implements DAO<NotificacaoUsuario> {

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(NotificacaoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO NOTIFICACAO_USUARIO (NOUS_ATIV, TINO_ID, USUA_ID) VALUES (?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setBoolean(1, obj.isAtiva());
            p.setInt(2, obj.getTipoNotificacao().getCodigo());
            p.setInt(3, obj.getUsuario().getCodigo());
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
                    = "DELETE FROM NOTIFICACAO_USUARIO WHERE NOUS_ID = ?";
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
    public void Alterar(NotificacaoUsuario obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE NOTIFICACAO_USUARIO SET NOUS_ATIV = ?, TINO_ID = ?, USUA_ID = ? WHERE NOUS_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setBoolean(1, obj.isAtiva());
            p.setInt(2, obj.getTipoNotificacao().getCodigo());
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
    public ArrayList<NotificacaoUsuario> Consultar() throws SQLException {
        try {
            ArrayList<NotificacaoUsuario> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT N.NOUS_ID, N.NOUS_ATIV, N.TINO_ID, N.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "T.TINO_ID, T.TINO_DESC "
                    + "FROM NOTIFICACAO_USUARIO C "
                    + "INNER JOIN USUARIO U "
                    + "ON C.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_NOTIFICACAO T "
                    + "ON C.TINO_ID = T.TINO_ID ");
            while (rs.next()) {
                NotificacaoUsuario obj = new NotificacaoUsuario();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                TipoNotificacao tipoNotificacao = new TipoNotificacao();

                obj.setCodigo(rs.getInt("C.NOUS_ID"));
                obj.setAtiva(rs.getBoolean("C.NOUS_ATIV"));

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

                tipoNotificacao.setCodigo(rs.getInt("T.TINO_ID"));
                tipoNotificacao.setDescricao(rs.getString("T.TINO_DESC"));
                obj.setTipoNotificacao(tipoNotificacao);

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
    public NotificacaoUsuario Consultar(int codigo) throws SQLException {
        try {
            NotificacaoUsuario obj = null;
            bd.conectar();
            String strSQL = "SELECT C.NOUS_ID, C.NOUS_ATIV, C.TINO_ID, C.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "T.TINO_ID, T.TINO_DESC "
                    + "FROM NOTIFICACAO_USUARIO C "
                    + "INNER JOIN USUARIO U "
                    + "ON C.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_NOTIFICACAO T "
                    + "ON C.TINO_ID = T.TINO_ID "
                    + "WHERE C.NOUS_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new NotificacaoUsuario();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                TipoNotificacao tipoNotificacao = new TipoNotificacao();

                obj.setCodigo(rs.getInt("C.NOUS_ID"));
                obj.setAtiva(rs.getBoolean("C.NOUS_ATIV"));

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

                tipoNotificacao.setCodigo(rs.getInt("T.TINO_ID"));
                tipoNotificacao.setDescricao(rs.getString("T.TINO_DESC"));
                obj.setTipoNotificacao(tipoNotificacao);

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
