/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.Noticia;
import Bean.StatusNoticia;
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
public class NoticiaDAO implements DAO<Noticia>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(Noticia obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO NOTICIA (NOTI_TITU, NOTI_DESC, NOTI_DT_PUBL, NOTI_DT_AGEN, NOTI_VISI, STNO_ID, USUA_ID) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getTitulo());
            p.setString(2, obj.getDescricao());
            p.setDate(3, obj.getDataPublicacao());
            p.setDate(4, obj.getDataAgendamento());
            p.setBoolean(5, obj.isVisivel());
            p.setInt(6, obj.getStatusNoticia().getCodigo());
            p.setInt(7, obj.getUsuario().getCodigo());
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
                    = "DELETE FROM NOTICIA WHERE NOTI_ID = ?";
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
    public void Alterar(Noticia obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE NOTICIA SET NOTI_TITU = ?, NOTI_DESC = ?, NOTI_DT_PUBL = ?, NOTI_DT_AGEN = ?, NOTI_VISI = ?, STNO_ID = ?, USUA_ID = ? WHERE NOTI_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getTitulo());
            p.setString(2, obj.getDescricao());
            p.setDate(3, obj.getDataPublicacao());
            p.setDate(4, obj.getDataAgendamento());
            p.setBoolean(5, obj.isVisivel());
            p.setInt(6, obj.getStatusNoticia().getCodigo());
            p.setInt(7, obj.getUsuario().getCodigo());
            p.setInt(8, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<Noticia> Consultar() throws SQLException {
        try {
            ArrayList<Noticia> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT N.NOTI_ID, N.NOTI_TITU, N.NOTI_DESC, N.NOTI_DT_PUBL, N.NOTI_DT_AGEN, N.NOTI_VISI, N.STNO_ID, N.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "S.STNO_ID, S.STNO_DESC "
                    + "FROM NOTICIA N "
                    + "INNER JOIN USUARIO U "
                    + "ON N.USUA_ID = U.USUA_ID "
                    + "INNER JOIN STATUS_NOTICIA S "
                    + "ON N.STNO_ID = S.STNO_ID ");
            while (rs.next()) {
                Noticia obj = new Noticia();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusNoticia statusNoticia = new StatusNoticia();

                obj.setCodigo(rs.getInt("N.NOTI_ID"));
                obj.setTitulo(rs.getString("N.NOTI_TITU"));
                obj.setDescricao(rs.getString("N.NOTI_DESC"));
                obj.setDataPublicacao(rs.getDate("N.NOTI_DT_PUBL"));
                obj.setDataAgendamento(rs.getDate("N.NOTI_DT_AGEN"));
                obj.setVisivel(rs.getBoolean("N.NOTI_VISI"));

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

                statusNoticia.setCodigo(rs.getInt("S.STNO_ID"));
                statusNoticia.setDescricao(rs.getString("S.STNO_DESC"));
                obj.setStatusNoticia(statusNoticia);

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
    public Noticia Consultar(int codigo) throws SQLException {
        try {
            Noticia obj = null;
            bd.conectar();
            String strSQL = "SELECT N.NOTI_ID, N.NOTI_TITU, N.NOTI_DESC, N.NOTI_DT_PUBL, N.NOTI_DT_AGEN, N.NOTI_VISI, N.STNO_ID, N.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "S.STNO_ID, S.STNO_DESC "
                    + "FROM NOTICIA C "
                    + "INNER JOIN USUARIO U "
                    + "ON N.USUA_ID = U.USUA_ID "
                    + "INNER JOIN STATUS_NOTICIA S "
                    + "ON N.STNO_ID = S.STNO_ID "
                    + "WHERE N.NOTI_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Noticia();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusNoticia statusNoticia = new StatusNoticia();

                obj.setCodigo(rs.getInt("N.NOTI_ID"));
                obj.setTitulo(rs.getString("N.NOTI_TITU"));
                obj.setDescricao(rs.getString("N.NOTI_DESC"));
                obj.setDataPublicacao(rs.getDate("N.NOTI_DT_PUBL"));
                obj.setDataAgendamento(rs.getDate("N.NOTI_DT_AGEN"));
                obj.setVisivel(rs.getBoolean("N.NOTI_VISI"));

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

                statusNoticia.setCodigo(rs.getInt("S.STNO_ID"));
                statusNoticia.setDescricao(rs.getString("S.STNO_DESC"));
                obj.setStatusNoticia(statusNoticia);

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
