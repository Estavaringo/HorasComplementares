/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Historico;
import Bean.RelatorioAtividade;
import Bean.RelatorioFinal;
import Bean.StatusRelatorioAtividade;
import Bean.TipoRelatorio;
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
public class HistoricoDAO implements DAO<Historico>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(Historico obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO HISTORICO (HIST_DT, REAT_ID, STRA_ID) VALUES (?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setDate(1, obj.getData());
            p.setInt(2, obj.getRelatorioAtividade().getCodigo());
            p.setInt(3, obj.getStatusRelatorioAtividade().getCodigo());
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
                    = "DELETE FROM HISTORICO WHERE HIST_ID = ?";
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
    public void Alterar(Historico obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE HISTORICO SET HIST_DT = ?, REAT_ID = ?, STRA_ID = ? WHERE HIST_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setDate(1, obj.getData());
            p.setInt(2, obj.getRelatorioAtividade().getCodigo());
            p.setInt(3, obj.getStatusRelatorioAtividade().getCodigo());
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
    public ArrayList<Historico> Consultar() throws SQLException {
        try {
            ArrayList<Historico> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT H.HIST_ID, H.HIST_DT, H.REAT_ID, H.STRA_ID, "
                    + "R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "S.STRA_ID, S.STRA_DESC "
                    + "FROM HISTORICO H "
                    + "INNER JOIN RELATORIO_ATIVIDADE R "
                    + "ON H.REAT_ID = R.REAT_ID "
                    + "INNER JOIN STATUS_RELATORIO_ATIVIDADE S "
                    + "ON H.STRA_ID = S.STRA_ID");
            while (rs.next()) {
                Historico obj = new Historico();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                StatusRelatorioAtividade statusRelatorioAtividadeHistorico = new StatusRelatorioAtividade();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();

                obj.setCodigo(rs.getInt("H.HIST_ID"));
                obj.setData(rs.getDate("H.HIST_DT"));
                
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

                statusRelatorioAtividadeHistorico.setCodigo(rs.getInt("S.STRA_ID"));
                statusRelatorioAtividadeHistorico.setDescricao(rs.getString("S.STRA_DESC"));
                obj.setStatusRelatorioAtividade(statusRelatorioAtividadeHistorico);
                
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
    public Historico Consultar(int codigo) throws SQLException {
        try {
            Historico obj = null;
            bd.conectar();
            String strSQL = "SELECT H.HIST_ID, H.HIST_DT, H.REAT_ID, H.STRA_ID, "
                    + "R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "S.STRA_ID, S.STRA_DESC "
                    + "FROM HISTORICO H "
                    + "INNER JOIN RELATORIO_ATIVIDADE R "
                    + "ON H.REAT_ID = R.REAT_ID "
                    + "INNER JOIN STATUS_RELATORIO_ATIVIDADE S "
                    + "ON H.STRA_ID = S.STRA_ID "
                    + "WHERE H.HIST_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Historico();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                StatusRelatorioAtividade statusRelatorioAtividadeHistorico = new StatusRelatorioAtividade();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();

                obj.setCodigo(rs.getInt("H.HIST_ID"));
                obj.setData(rs.getDate("H.HIST_DT"));
                
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

                statusRelatorioAtividadeHistorico.setCodigo(rs.getInt("S.STRA_ID"));
                statusRelatorioAtividadeHistorico.setDescricao(rs.getString("S.STRA_DESC"));
                obj.setStatusRelatorioAtividade(statusRelatorioAtividadeHistorico);
             
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
