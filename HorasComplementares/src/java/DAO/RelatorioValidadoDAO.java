/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import Bean.RelatorioAtividade;
import Bean.RelatorioFinal;
import Bean.RelatorioValidado;
import Bean.StatusRelatorioAtividade;
import Bean.TipoComprovante;
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
public class RelatorioValidadoDAO implements DAO<RelatorioValidado>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(RelatorioValidado obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO RELATORIO_VALIDADO (REVA_COME, REVA_DT, REAT_HR, REAT_ID, USUA_ID) VALUES (?,?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getComentario());
            p.setDate(2, obj.getDataValidacao());
            p.setInt(3, obj.getHorasValidadas());
            p.setInt(4, obj.getRelatorioAtividade().getCodigo());
            p.setInt(5, obj.getUsuario().getCodigo());
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
                    = "DELETE FROM RELATORIO_VALIDADO WHERE REVA_ID = ?";
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
    public void Alterar(RelatorioValidado obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE RELATORIO_VALIDADO SET REVA_COME = ?, REVA_DT = ?, REAT_HR = ?, REAT_ID = ?, USUA_ID = ? WHERE REVA_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getComentario());
            p.setDate(2, obj.getDataValidacao());
            p.setInt(3, obj.getHorasValidadas());
            p.setInt(4, obj.getRelatorioAtividade().getCodigo());
            p.setInt(5, obj.getUsuario().getCodigo());
            p.setInt(5, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<RelatorioValidado> Consultar() throws SQLException {
        try {
            ArrayList<RelatorioValidado> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT R.REVA_ID, R.REVA_COME, R.REVA_DT, R.REVA_HR, R.REAT_ID, R.USUA_ID, "
                    + "RA.REAT_ID, RA.REAT_DESC, RA.REAT_SEME, RA.REAT_RESU, RA.REAT_DT, RA.TIRE_ID, RA.STRA_ID, RA.REFI_ID, RA.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "FROM RELATORIO_VALIDADO R "
                    + "INNER JOIN RELATORIO_ATIVIDADE RA "
                    + "ON R.REAT_ID = RA.REAT_ID "
                    + "INNER JOIN USUARIO U "
                    + "ON R.USUA_ID = U.USUA_ID_ID");
            while (rs.next()) {
                RelatorioValidado obj = new RelatorioValidado();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();

                obj.setCodigo(rs.getInt("R.REVA_ID"));
                obj.setComentario(rs.getString("R.REVA_COME"));
                obj.setDataValidacao(rs.getDate("R.REVA_DT"));
                obj.setHorasValidadas(rs.getInt("R.REVA_HR"));
                
                relatorioAtividade.setCodigo(rs.getInt("R.REAT_ID"));
                relatorioAtividade.setDescricao(rs.getString("R.REAT_DESC"));
                relatorioAtividade.setSemestre(rs.getString("R.REAT_SEME"));
                relatorioAtividade.setResumo(rs.getString("R.REAT_RESU"));
                relatorioAtividade.setData(rs.getDate("R.REAT_DT"));
                tipoRelatorio.setCodigo(rs.getInt("R.TIRE_ID"));
                relatorioAtividade.setTipoRelatorio(tipoRelatorio);
                statusRelatorioAtividade.setCodigo(rs.getInt("R.STRA_ID"));
                relatorioAtividade.setStatusRelatorioAtividade(statusRelatorioAtividade);
                relatorioFinal.setCodigo(rs.getInt("R.REFI_ID"));
                relatorioAtividade.setRelatorioFinal(relatorioFinal);
                usuario.setCodigo(rs.getInt("R.USUA_ID"));
                relatorioAtividade.setUsuario(usuario);
                obj.setRelatorioAtividade(relatorioAtividade);

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
    public RelatorioValidado Consultar(int codigo) throws SQLException {
        try {
            RelatorioValidado obj = null;
            bd.conectar();
            String strSQL = "SELECT R.REVA_ID, R.REVA_COME, R.REVA_DT, R.REVA_HR, R.REAT_ID, R.USUA_ID, "
                    + "RA.REAT_ID, RA.REAT_DESC, RA.REAT_SEME, RA.REAT_RESU, RA.REAT_DT, RA.TIRE_ID, RA.STRA_ID, RA.REFI_ID, RA.USUA_ID, "
                    + "U.USUA_ID, U.USUA_NM, U.USUA_PRON, U.USUA_FUNC, U.USUA_DT_INI, U.USUA_SEME, U.CURS_ID, U.TIUS_ID, U.USUA_ATIVO, "
                    + "FROM RELATORIO_VALIDADO R "
                    + "INNER JOIN RELATORIO_ATIVIDADE RA "
                    + "ON R.REAT_ID = RA.REAT_ID "
                    + "INNER JOIN USUARIO U "
                    + "ON R.USUA_ID = U.USUA_ID_ID "
                    + "WHERE D.REVA_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new RelatorioValidado();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();
                Curso curso = new Curso();
                TipoUsuario tipoUsuario = new TipoUsuario();

                obj.setCodigo(rs.getInt("R.REVA_ID"));
                obj.setComentario(rs.getString("R.REVA_COME"));
                obj.setDataValidacao(rs.getDate("R.REVA_DT"));
                obj.setHorasValidadas(rs.getInt("R.REVA_HR"));
                
                relatorioAtividade.setCodigo(rs.getInt("R.REAT_ID"));
                relatorioAtividade.setDescricao(rs.getString("R.REAT_DESC"));
                relatorioAtividade.setSemestre(rs.getString("R.REAT_SEME"));
                relatorioAtividade.setResumo(rs.getString("R.REAT_RESU"));
                relatorioAtividade.setData(rs.getDate("R.REAT_DT"));
                tipoRelatorio.setCodigo(rs.getInt("R.TIRE_ID"));
                relatorioAtividade.setTipoRelatorio(tipoRelatorio);
                statusRelatorioAtividade.setCodigo(rs.getInt("R.STRA_ID"));
                relatorioAtividade.setStatusRelatorioAtividade(statusRelatorioAtividade);
                relatorioFinal.setCodigo(rs.getInt("R.REFI_ID"));
                relatorioAtividade.setRelatorioFinal(relatorioFinal);
                usuario.setCodigo(rs.getInt("R.USUA_ID"));
                relatorioAtividade.setUsuario(usuario);
                obj.setRelatorioAtividade(relatorioAtividade);

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
