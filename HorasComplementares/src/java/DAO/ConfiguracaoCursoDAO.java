/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.RelatorioAtividade;
import Bean.RelatorioFinal;
import Bean.StatusRelatorioAtividade;
import Bean.StatusRelatorioFinal;
import Bean.TipoRelatorio;
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
public class ConfiguracaoCursoDAO implements DAO<RelatorioAtividade>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(RelatorioAtividade obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO RELATORIO_ATIVIDADE (REAT_DESC, REAT_SEME, REAT_RESU, REAT_DT, TIRE_ID, STRA_ID, REFI_ID, USUA_ID) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getSemestre());
            p.setString(3, obj.getResumo());
            p.setDate(4, obj.getData());
            p.setInt(5, obj.getTipoRelatorio().getCodigo());
            p.setInt(6, obj.getStatusRelatorioAtividade().getCodigo());
            p.setInt(7, obj.getRelatorioFinal().getCodigo());
            p.setInt(8, obj.getUsuario().getCodigo());
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
                    = "DELETE FROM RELATORIO_ATIVIDADE WHERE REAT_ID = ?";
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
    public void Alterar(RelatorioAtividade obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE RELATORIO_ATIVIDADE SET REAT_DESC = ?, REAT_SEME = ?, REAT_RESU = ?, REAT_DT = ?, "
                    + "TIRE_ID = ?, STRA_ID = ?, REFI_ID = ?, USUA_ID = ? WHERE REAT_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getSemestre());
            p.setString(3, obj.getResumo());
            p.setDate(4, obj.getData());
            p.setInt(5, obj.getTipoRelatorio().getCodigo());
            p.setInt(6, obj.getStatusRelatorioAtividade().getCodigo());
            p.setInt(7, obj.getRelatorioFinal().getCodigo());
            p.setInt(8, obj.getUsuario().getCodigo());
            p.setInt(9, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<RelatorioAtividade> Consultar() throws SQLException {
        try {
            ArrayList<RelatorioAtividade> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "S.STRA_ID, S.STRA_DESC, "
                    + "T.TIRE_ID, T.TIRE_DESC, "
                    + "RF.REFI_ID, RF.REFI_DT_EMIS, RF.REFI_HR_TOTAL, RF.REFI_DT_SOLI, RF.USUA_ID_SOLI, RF.USUA_ID_EMIS, RF.STRF_ID "
                    + "FROM RELATORIO_ATIVIDADE R "
                    + "INNER JOIN USUARIO U "
                    + "ON R.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_RELATORIO T "
                    + "ON R.TIRE_ID = T.TIRE_ID "
                    + "INNER JOIN RELATORIO_FINAL RF "
                    + "ON R.REFI_ID = RF.REFI_ID "
                    + "INNER JOIN STATUS_RELATORIO_ATIVIDADE X "
                    + "ON R.STRA_ID = X.STRA_ID ");
            while (rs.next()) {
                RelatorioAtividade obj = new RelatorioAtividade();
                Usuario usuario = new Usuario();
                Usuario usuarioEmissao = new Usuario();
                Usuario usuarioSolicitacao = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();
                StatusRelatorioFinal statusRelatorioFinal = new StatusRelatorioFinal();

                obj.setCodigo(rs.getInt("R.REAT_ID"));
                obj.setDescricao(rs.getString("R.REAT_DESC"));
                obj.setSemestre(rs.getString("R.REAT_SEME"));
                obj.setResumo(rs.getString("R.REAT_RESU"));
                obj.setData(rs.getDate("R.REAT_DT"));
                

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

                tipoRelatorio.setCodigo(rs.getInt("T.TIRE_ID"));
                tipoRelatorio.setDescricao(rs.getString("T.TIRE_DESC"));
                obj.setTipoRelatorio(tipoRelatorio);

                statusRelatorioAtividade.setCodigo(rs.getInt("X.STRA_ID"));
                statusRelatorioAtividade.setDescricao(rs.getString("X.STRA_DESC"));
                obj.setStatusRelatorioAtividade(statusRelatorioAtividade);

                relatorioFinal.setCodigo(rs.getInt("RF.REFI_ID"));
                relatorioFinal.setDataEmissao(rs.getDate("RF.REFI_DT_EMIS"));
                relatorioFinal.setDataSolicitacao(rs.getDate("RF.REFI_DT_SOLI"));
                relatorioFinal.setHoras(rs.getInt("RF.REFI_HR_TOTAL"));
                statusRelatorioFinal.setCodigo(rs.getInt("RF.STRF_ID"));
                relatorioFinal.setStatusRelatorioFinal(statusRelatorioFinal);
                usuarioEmissao.setCodigo(rs.getInt("RF.USUA_ID_EMIS"));
                relatorioFinal.setUsuarioEmissao(usuarioEmissao);
                usuarioSolicitacao.setCodigo(rs.getInt("RF.USUA_ID_SOLI"));
                relatorioFinal.setUsuarioSolicitacao(usuarioSolicitacao);
                obj.setRelatorioFinal(relatorioFinal);
                
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
    public RelatorioAtividade Consultar(int codigo) throws SQLException {
        try {
            RelatorioAtividade obj = null;
            bd.conectar();
            String strSQL = "SELECT R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "S.STRA_ID, S.STRA_DESC, "
                    + "T.TIRE_ID, T.TIRE_DESC, "
                    + "RF.REFI_ID, RF.REFI_DT_EMIS, RF.REFI_HR_TOTAL, RF.REFI_DT_SOLI, RF.USUA_ID_SOLI, RF.USUA_ID_EMIS, RF.STRF_ID "
                    + "FROM RELATORIO_ATIVIDADE R "
                    + "INNER JOIN USUARIO U "
                    + "ON R.USUA_ID = U.USUA_ID "
                    + "INNER JOIN TIPO_RELATORIO T "
                    + "ON R.TIRE_ID = T.TIRE_ID "
                    + "INNER JOIN RELATORIO_FINAL RF "
                    + "ON R.REFI_ID = RF.REFI_ID "
                    + "INNER JOIN STATUS_RELATORIO_ATIVIDADE X "
                    + "ON R.STRA_ID = X.STRA_ID "
                    + "WHERE R.REAT_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new RelatorioAtividade();
                Usuario usuario = new Usuario();
                Usuario usuarioEmissao = new Usuario();
                Usuario usuarioSolicitacao = new Usuario();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();
                StatusRelatorioFinal statusRelatorioFinal = new StatusRelatorioFinal();

                obj.setCodigo(rs.getInt("R.REAT_ID"));
                obj.setDescricao(rs.getString("R.REAT_DESC"));
                obj.setSemestre(rs.getString("R.REAT_SEME"));
                obj.setResumo(rs.getString("R.REAT_RESU"));
                obj.setData(rs.getDate("R.REAT_DT"));
                

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

                tipoRelatorio.setCodigo(rs.getInt("T.TIRE_ID"));
                tipoRelatorio.setDescricao(rs.getString("T.TIRE_DESC"));
                obj.setTipoRelatorio(tipoRelatorio);

                statusRelatorioAtividade.setCodigo(rs.getInt("X.STRA_ID"));
                statusRelatorioAtividade.setDescricao(rs.getString("X.STRA_DESC"));
                obj.setStatusRelatorioAtividade(statusRelatorioAtividade);

                relatorioFinal.setCodigo(rs.getInt("RF.REFI_ID"));
                relatorioFinal.setDataEmissao(rs.getDate("RF.REFI_DT_EMIS"));
                relatorioFinal.setDataSolicitacao(rs.getDate("RF.REFI_DT_SOLI"));
                relatorioFinal.setHoras(rs.getInt("RF.REFI_HR_TOTAL"));
                statusRelatorioFinal.setCodigo(rs.getInt("RF.STRF_ID"));
                relatorioFinal.setStatusRelatorioFinal(statusRelatorioFinal);
                usuarioEmissao.setCodigo(rs.getInt("RF.USUA_ID_EMIS"));
                relatorioFinal.setUsuarioEmissao(usuarioEmissao);
                usuarioSolicitacao.setCodigo(rs.getInt("RF.USUA_ID_SOLI"));
                relatorioFinal.setUsuarioSolicitacao(usuarioSolicitacao);
                obj.setRelatorioFinal(relatorioFinal);
             
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
