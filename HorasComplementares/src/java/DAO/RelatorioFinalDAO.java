/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.RelatorioFinal;
import Bean.StatusRelatorioFinal;
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
public class RelatorioFinalDAO implements DAO<RelatorioFinal> {

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(RelatorioFinal obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO RELATORIO_FINAL (REFI_DT_EMIS, REFI_HR_TOTAL, REFI_DT_SOLI, USUA_ID_SOLI, USUA_ID_EMIS, STRF_ID) VALUES (?,?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setDate(1, obj.getDataEmissao());
            p.setInt(2, obj.getHoras());
            p.setDate(3, obj.getDataSolicitacao());
            p.setInt(4, obj.getUsuarioSolicitacao().getCodigo());
            p.setInt(5, obj.getUsuarioEmissao().getCodigo());
            p.setInt(6, obj.getStatusRelatorioFinal().getCodigo());
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
                    = "DELETE FROM RELATORIO_FINAL WHERE REFI_ID = ?";
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
    public void Alterar(RelatorioFinal obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE RELATORIO_FINAL SET REFI_DT_EMIS = ?, REFI_HR_TOTAL = ?, REFI_DT_SOLI = ?, USUA_ID_SOLI = ?, USUA_ID_EMIS = ?, STRF_ID = ? WHERE REFI_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setDate(1, obj.getDataEmissao());
            p.setInt(2, obj.getHoras());
            p.setDate(3, obj.getDataSolicitacao());
            p.setInt(4, obj.getUsuarioSolicitacao().getCodigo());
            p.setInt(5, obj.getUsuarioEmissao().getCodigo());
            p.setInt(6, obj.getStatusRelatorioFinal().getCodigo());
            p.setInt(7, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<RelatorioFinal> Consultar() throws SQLException {
        try {
            ArrayList<RelatorioFinal> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT R.REFI_ID, R.REFI_DT_EMIS, R.REFI_HR_TOTAL, R.REFI_DT_SOLI, R.USUA_ID_SOLI, R.USUA_ID_EMIS, R.STRF_ID, "
                    + "E.USUA_ID, E.USUA_NM, E.USUA_PRON, E.USUA_FUNC, E.USUA_DT_INI, E.USUA_SEME, E.CURS_ID, E.TIUS_ID, E.USUA_ATIVO, "
                    + "S.USUA_ID, S.USUA_NM, S.USUA_PRON, S.USUA_FUNC, S.USUA_DT_INI, S.USUA_SEME, S.CURS_ID, S.TIUS_ID, S.USUA_ATIVO, "
                    + "X.STRF_ID, X.STRF_DESC "
                    + "FROM RELATORIO_FINAL R "
                    + "INNER JOIN USUARIO E "
                    + "ON R.USUA_ID_EMIS = E.USUA_ID "
                    + "INNER JOIN USUARIO S "
                    + "ON R.USUA_ID_SOLI = S.USUA_ID "
                    + "INNER JOIN STATUS_RELATORIO_FINAL X "
                    + "ON R.STRF_ID = X.STRF_ID ");
            while (rs.next()) {
                RelatorioFinal obj = new RelatorioFinal();
                Usuario usuarioEmissao = new Usuario();
                Usuario usuarioSolicitacao = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusRelatorioFinal statusRelatorioFinal = new StatusRelatorioFinal();

                obj.setCodigo(rs.getInt("R.REFI_ID"));
                obj.setDataEmissao(rs.getDate("R.REFI_DT_EMIS"));
                obj.setDataSolicitacao(rs.getDate("R.REFI_DT_SOLI"));
                obj.setHoras(rs.getInt("R.REFI_HR_TOTAL"));
                

                usuarioEmissao.setCodigo(rs.getInt("E.USUA_ID"));
                usuarioEmissao.setNome(rs.getString("E.USUA_NM"));
                usuarioEmissao.setProntuario(rs.getString("E.USUA_PRON"));
                usuarioEmissao.setFuncional(rs.getString("E.USUA_FUNC"));
                usuarioEmissao.setDataMatricula(rs.getDate("E.USUA_DT_INI"));
                usuarioEmissao.setSemestre(rs.getString("E.USUA_SEME"));
                usuarioEmissao.setAtivo(rs.getBoolean("E.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("E.CURS_ID"));
                usuarioEmissao.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("E.TIUS_ID"));
                usuarioEmissao.setTipoUsuario(tipoUsuario);
                obj.setUsuarioEmissao(usuarioEmissao);

                usuarioSolicitacao.setCodigo(rs.getInt("S.USUA_ID"));
                usuarioSolicitacao.setNome(rs.getString("S.USUA_NM"));
                usuarioSolicitacao.setProntuario(rs.getString("S.USUA_PRON"));
                usuarioSolicitacao.setFuncional(rs.getString("S.USUA_FUNC"));
                usuarioSolicitacao.setDataMatricula(rs.getDate("S.USUA_DT_INI"));
                usuarioSolicitacao.setSemestre(rs.getString("S.USUA_SEME"));
                usuarioSolicitacao.setAtivo(rs.getBoolean("S.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("S.CURS_ID"));
                usuarioSolicitacao.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("S.TIUS_ID"));
                usuarioSolicitacao.setTipoUsuario(tipoUsuario);
                obj.setUsuarioSolicitacao(usuarioSolicitacao);

                statusRelatorioFinal.setCodigo(rs.getInt("X.STRF_ID"));
                statusRelatorioFinal.setDescricao(rs.getString("X.STRF_DESC"));
                obj.setStatusRelatorioFinal(statusRelatorioFinal);

             
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
    public RelatorioFinal Consultar(int codigo) throws SQLException {
        try {
            RelatorioFinal obj = null;
            bd.conectar();
            String strSQL = "SELECT R.REFI_ID, R.REFI_DT_EMIS, R.REFI_HR_TOTAL, R.REFI_DT_SOLI, R.USUA_ID_SOLI, R.USUA_ID_EMIS, R.STRF_ID, "
                    + "E.USUA_ID, E.USUA_NM, E.USUA_PRON, E.USUA_FUNC, E.USUA_DT_INI, E.USUA_SEME, E.CURS_ID, E.TIUS_ID, E.USUA_ATIVO, "
                    + "S.USUA_ID, S.USUA_NM, S.USUA_PRON, S.USUA_FUNC, S.USUA_DT_INI, S.USUA_SEME, S.CURS_ID, S.TIUS_ID, S.USUA_ATIVO, "
                    + "X.STRF_ID, X.STRF_DESC "
                    + "FROM RELATORIO_FINAL R "
                    + "INNER JOIN USUARIO E "
                    + "ON R.USUA_ID_EMIS = E.USUA_ID "
                    + "INNER JOIN USUARIO S "
                    + "ON R.USUA_ID_SOLI = S.USUA_ID "
                    + "INNER JOIN STATUS_RELATORIO_FINAL X "
                    + "ON R.STRF_ID = X.STRF_ID "
                    + "WHERE R.REFI_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new RelatorioFinal();
                Usuario usuarioEmissao = new Usuario();
                Usuario usuarioSolicitacao = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusRelatorioFinal statusRelatorioFinal = new StatusRelatorioFinal();

                obj.setCodigo(rs.getInt("R.REFI_ID"));
                obj.setDataEmissao(rs.getDate("R.REFI_DT_EMIS"));
                obj.setDataSolicitacao(rs.getDate("R.REFI_DT_SOLI"));
                obj.setHoras(rs.getInt("R.REFI_HR_TOTAL"));
                

                usuarioEmissao.setCodigo(rs.getInt("E.USUA_ID"));
                usuarioEmissao.setNome(rs.getString("E.USUA_NM"));
                usuarioEmissao.setProntuario(rs.getString("E.USUA_PRON"));
                usuarioEmissao.setFuncional(rs.getString("E.USUA_FUNC"));
                usuarioEmissao.setDataMatricula(rs.getDate("E.USUA_DT_INI"));
                usuarioEmissao.setSemestre(rs.getString("E.USUA_SEME"));
                usuarioEmissao.setAtivo(rs.getBoolean("E.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("E.CURS_ID"));
                usuarioEmissao.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("E.TIUS_ID"));
                usuarioEmissao.setTipoUsuario(tipoUsuario);
                obj.setUsuarioEmissao(usuarioEmissao);

                usuarioSolicitacao.setCodigo(rs.getInt("S.USUA_ID"));
                usuarioSolicitacao.setNome(rs.getString("S.USUA_NM"));
                usuarioSolicitacao.setProntuario(rs.getString("S.USUA_PRON"));
                usuarioSolicitacao.setFuncional(rs.getString("S.USUA_FUNC"));
                usuarioSolicitacao.setDataMatricula(rs.getDate("S.USUA_DT_INI"));
                usuarioSolicitacao.setSemestre(rs.getString("S.USUA_SEME"));
                usuarioSolicitacao.setAtivo(rs.getBoolean("S.USUA_ATIVO"));
                curso.setCodigo(rs.getInt("S.CURS_ID"));
                usuarioSolicitacao.setCurso(curso);
                tipoUsuario.setCodigo(rs.getInt("S.TIUS_ID"));
                usuarioSolicitacao.setTipoUsuario(tipoUsuario);
                obj.setUsuarioSolicitacao(usuarioSolicitacao);

                statusRelatorioFinal.setCodigo(rs.getInt("X.STRF_ID"));
                statusRelatorioFinal.setDescricao(rs.getString("X.STRF_DESC"));
                obj.setStatusRelatorioFinal(statusRelatorioFinal);
             
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
