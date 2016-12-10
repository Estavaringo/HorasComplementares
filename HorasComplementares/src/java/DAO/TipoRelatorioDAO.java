/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.TipoRelatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoRelatorioDAO implements DAO<TipoRelatorio>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(TipoRelatorio obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO TIPO_RELATORIO (TIRE_DESC, TIRE_HR) VALUES (?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getQtdHoras());
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
                    = "DELETE FROM TIPO_RELATORIO WHERE TIRE_ID = ?";
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
    public void Alterar(TipoRelatorio obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE TIPO_RELATORIO SET TIRE_DESC = ?, TIRE_HR = ? WHERE TIRE_ID = ?";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getQtdHoras());
            p.setInt(3, obj.getCodigo());
            p.execute();
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }
    }

    @Override
    public ArrayList<TipoRelatorio> Consultar() throws SQLException {
        try {
            ArrayList<TipoRelatorio> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT TIRE_ID, TIRE_DESC, TIRE_HR FROM TIPO_RELATORIO");
            while (rs.next()) {
                TipoRelatorio obj = new TipoRelatorio();
                obj.setCodigo(rs.getInt("TIRE_ID"));
                obj.setDescricao(rs.getString("TIRE_DESC"));
                obj.setQtdHoras(rs.getInt("TIRE_HR"));
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
    public TipoRelatorio Consultar(int codigo) throws SQLException {
        try {
            TipoRelatorio obj = null;
            bd.conectar();
            String strSQL = "SELECT TIRE_ID, TIRE_DESC, TIRE_HR FROM TIPO_RELATORIO WHERE TIRE_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoRelatorio();
                obj.setCodigo(rs.getInt("TIRE_ID"));
                obj.setDescricao(rs.getString("TIRE_DESC"));
                obj.setQtdHoras(rs.getInt("TIRE_HR"));
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
    
    public TipoRelatorio ConsultarCurso(int codigoCurso) throws SQLException {
        try {
            TipoRelatorio obj = null;
            bd.conectar();
            String strSQL = "SELECT TIRE_ID, TIRE_DESC, TIRE_HR FROM TIPO_RELATORIO WHERE TIRE_CURSO_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigoCurso);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new TipoRelatorio();
                obj.setCodigo(rs.getInt("TIRE_ID"));
                obj.setDescricao(rs.getString("TIRE_DESC"));
                obj.setQtdHoras(rs.getInt("TIRE_HR"));
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
