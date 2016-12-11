/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.Noticia;
import Bean.NoticiaCurso;
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
public class NoticiaCursoDAO implements DAO<NoticiaCurso>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(NoticiaCurso obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO NOTICIA_CURSO (NOCU_DESC, CURS_ID, NOTI_ID) VALUES (?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getCurso().getCodigo());
            p.setInt(3, obj.getNoticia().getCodigo());
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
                    = "DELETE FROM NOTICIA_CURSO WHERE NOCU_ID = ?";
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
    public void Alterar(NoticiaCurso obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE NOTICIA_CURSO SET NOCU_DESC = ?, CURS_ID = ?, NOTI_ID = ? WHERE NOCU_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getCurso().getCodigo());
            p.setInt(3, obj.getNoticia().getCodigo());
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
    public ArrayList<NoticiaCurso> Consultar() throws SQLException {
        try {
            ArrayList<NoticiaCurso> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT N.NOCU_ID, N.NOCU_DESC, N.CURS_ID, N.NOTI_ID, "
                    + "C.CURS_ID, C.CURS_DESC, C.CURS_HR_NECE, "
                    + "X.NOTI_ID, X.NOTI_TITU, X.NOTI_DESC, X.NOTI_DT_PUBL, X.NOTI_DT_AGEN, X.NOTI_VISI, X.STNO_ID, X.USUA_ID "
                    + "FROM NOTICIA_CURSO N "
                    + "INNER JOIN CURSO C "
                    + "ON N.CURS_ID = C.CURS_ID "
                    + "INNER JOIN NOTICIA X "
                    + "ON N.NOTI_ID = X.NOTI_ID ");
            while (rs.next()) {
                NoticiaCurso obj = new NoticiaCurso();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                Noticia noticia = new Noticia();
                StatusNoticia statusNoticia = new StatusNoticia();

                obj.setCodigo(rs.getInt("N.NOCU_ID"));
                obj.setDescricao(rs.getString("N.NOCU_DESC"));

                curso.setCodigo(rs.getInt("C.USUA_ID"));
                curso.setDescricao(rs.getString("C.CURS_DESC"));
              //  curso.setCargaHoraria(rs.getInt("C.CURS_HR_NECE"));
                obj.setCurso(curso);

                noticia.setCodigo(rs.getInt("X.NOTI_ID"));
                noticia.setTitulo(rs.getString("X.NOTI_TITU"));
                noticia.setDescricao(rs.getString("X.NOTI_DESC"));
                noticia.setDataPublicacao(rs.getDate("X.NOTI_DT_PUBL"));
                noticia.setDataAgendamento(rs.getDate("X.NOTI_DT_AGEN"));
                noticia.setVisivel(rs.getBoolean("X.NOTI_VISI"));
                statusNoticia.setCodigo(rs.getInt("X.STNO_ID"));
                usuario.setCodigo(rs.getInt("X.USUA_ID"));
                noticia.setStatusNoticia(statusNoticia);
                noticia.setUsuario(usuario);
                
                obj.setNoticia(noticia);

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
    public NoticiaCurso Consultar(int codigo) throws SQLException {
        try {
            NoticiaCurso obj = null;
            bd.conectar();
            String strSQL = "SELECT N.NOCU_ID, N.NOCU_DESC, N.CURS_ID, N.NOTI_ID, "
                    + "C.CURS_ID, C.CURS_DESC, C.CURS_HR_NECE, "
                    + "X.NOTI_ID, X.NOTI_TITU, X.NOTI_DESC, X.NOTI_DT_PUBL, X.NOTI_DT_AGEN, X.NOTI_VISI, X.STNO_ID, X.USUA_ID "
                    + "FROM NOTICIA_CURSO N "
                    + "INNER JOIN CURSO C "
                    + "ON N.CURS_ID = C.CURS_ID "
                    + "INNER JOIN NOTICIA X "
                    + "ON N.NOTI_ID = X.NOTI_ID "
                    + "WHERE N.NOCU_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new NoticiaCurso();
                Usuario usuario = new Usuario();
                Curso curso = new Curso();
                Noticia noticia = new Noticia();
                StatusNoticia statusNoticia = new StatusNoticia();

                obj.setCodigo(rs.getInt("N.NOCU_ID"));
                obj.setDescricao(rs.getString("N.NOCU_DESC"));

                curso.setCodigo(rs.getInt("C.USUA_ID"));
                curso.setDescricao(rs.getString("C.CURS_DESC"));
               // curso.setCargaHoraria(rs.getInt("C.CURS_HR_NECE"));
                obj.setCurso(curso);

                noticia.setCodigo(rs.getInt("X.NOTI_ID"));
                noticia.setTitulo(rs.getString("X.NOTI_TITU"));
                noticia.setDescricao(rs.getString("X.NOTI_DESC"));
                noticia.setDataPublicacao(rs.getDate("X.NOTI_DT_PUBL"));
                noticia.setDataAgendamento(rs.getDate("X.NOTI_DT_AGEN"));
                noticia.setVisivel(rs.getBoolean("X.NOTI_VISI"));
                statusNoticia.setCodigo(rs.getInt("X.STNO_ID"));
                usuario.setCodigo(rs.getInt("X.USUA_ID"));
                noticia.setStatusNoticia(statusNoticia);
                noticia.setUsuario(usuario);
                
                obj.setNoticia(noticia);

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
