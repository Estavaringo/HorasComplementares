/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.DocumentoRelatorio;
import Bean.RelatorioAtividade;
import Bean.RelatorioFinal;
import Bean.StatusRelatorioAtividade;
import Bean.TipoComprovante;
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
public class DocumentoRelatorioDAO implements DAO<DocumentoRelatorio>{

    BancoDados bd = new BancoDados();

    @Override
    public void Incluir(DocumentoRelatorio obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO DOCUMENTO_RELATORIO (DORE_DESC, DORE_URL, REAT_ID, TIDO_ID) VALUES (?,?,?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getUrl());
            p.setInt(3, obj.getRelatorioAtividade().getCodigo());
            p.setInt(4, obj.getTipoDocumento().getCodigo());
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
                    = "DELETE FROM DOCUMENTO_RELATORIO WHERE DORE_ID = ?";
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
    public void Alterar(DocumentoRelatorio obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE DOCUMENTO_RELATORIO SET DORE_DESC = ?, DORE_URL = ?, REAT_ID = ?, TIDO_ID = ? WHERE DORE_ID = ? ";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setString(2, obj.getUrl());
            p.setInt(3, obj.getRelatorioAtividade().getCodigo());
            p.setInt(4, obj.getTipoDocumento().getCodigo());
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
    public ArrayList<DocumentoRelatorio> Consultar() throws SQLException {
        try {
            ArrayList<DocumentoRelatorio> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT D.DORE_ID, D.DORE_DESC, D.DORE_URL, D.REAT_ID, D.TIDO_ID, "
                    + "R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "T.TIDO_ID, T.TIDO_DESC "
                    + "FROM DOCUMENTO_RELATORIO D "
                    + "INNER JOIN RELATORIO_ATIVIDADE R "
                    + "ON D.REAT_ID = R.REAT_ID "
                    + "INNER JOIN TIPO_DOCUMENTO T "
                    + "ON D.TIDO_ID = T.TIDO_ID");
            while (rs.next()) {
                DocumentoRelatorio obj = new DocumentoRelatorio();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                TipoComprovante tipoDocumento = new TipoComprovante();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();

                obj.setCodigo(rs.getInt("D.DORE_ID"));
                obj.setDescricao(rs.getString("D.DORE_DESC"));
                obj.setUrl(rs.getString("D.DORE_URL"));
                
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

                tipoDocumento.setCodigo(rs.getInt("T.TIDO_ID"));
                tipoDocumento.setDescricao(rs.getString("T.TIDO_DESC"));
                obj.setTipoDocumento(tipoDocumento);
                
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
    public DocumentoRelatorio Consultar(int codigo) throws SQLException {
        try {
            DocumentoRelatorio obj = null;
            bd.conectar();
            String strSQL = "SELECT D.DORE_ID, D.DORE_DESC, D.DORE_URL, D.REAT_ID, D.TIDO_ID, "
                    + "R.REAT_ID, R.REAT_DESC, R.REAT_SEME, R.REAT_RESU, R.REAT_DT, R.TIRE_ID, R.STRA_ID, R.REFI_ID, R.USUA_ID, "
                    + "T.TIDO_ID, T.TIDO_DESC "
                    + "FROM DOCUMENTO_RELATORIO D "
                    + "INNER JOIN RELATORIO_ATIVIDADE R "
                    + "ON D.REAT_ID = R.REAT_ID "
                    + "INNER JOIN TIPO_DOCUMENTO T "
                    + "ON D.TIDO_ID = T.TIDO_ID "
                    + "WHERE D.DORE_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new DocumentoRelatorio();
                RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
                TipoComprovante tipoDocumento = new TipoComprovante();
                Usuario usuario = new Usuario();
                StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
                TipoRelatorio tipoRelatorio = new TipoRelatorio();
                RelatorioFinal relatorioFinal = new RelatorioFinal();

                obj.setCodigo(rs.getInt("D.DORE_ID"));
                obj.setDescricao(rs.getString("D.DORE_DESC"));
                obj.setUrl(rs.getString("D.DORE_URL"));
                
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

                tipoDocumento.setCodigo(rs.getInt("T.TIDO_ID"));
                tipoDocumento.setDescricao(rs.getString("T.TIDO_DESC"));
                obj.setTipoDocumento(tipoDocumento);
                
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
