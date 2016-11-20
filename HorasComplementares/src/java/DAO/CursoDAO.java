/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class CursoDAO implements DAO<Curso>{

    BancoDados bd = new BancoDados();
    
    @Override
    public void Incluir(Curso obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "INSERT INTO CURSO (CURS_DESC, CURS_HR_NECE) VALUES (?,?)";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getCargaHoraria());
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
                    = "DELETE FROM CURSO WHERE CURS_ID = ?";
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
    public void Alterar(Curso obj) throws SQLException {
        try {
            bd.conectar();
            String strSql
                    = "UPDATE CURSO SET CURS_DESC = ?, CURS_HR_NECE = ? WHERE CURS_ID = ?";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getDescricao());
            p.setInt(2, obj.getCargaHoraria());
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
    public ArrayList<Curso> Consultar() throws SQLException {
        try {
            ArrayList<Curso> lista = new ArrayList<>();
            bd.conectar();
            Statement comando;
            comando = bd.connection.createStatement();
            ResultSet rs = comando.executeQuery("SELECT CURS_ID, CURS_DESC, CURS_HR_NECE FROM CURSO");
            while (rs.next()) {
                Curso obj = new Curso();
                obj.setCodigo(rs.getInt("CURS_ID"));
                obj.setDescricao(rs.getString("CURS_DESC"));
                obj.setCargaHoraria(rs.getInt("CURS_HR_NECE"));
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
    public Curso Consultar(int codigo) throws SQLException {
        try {
            Curso obj = null;
            bd.conectar();
            String strSQL = "SELECT CURS_ID, CURS_DESC, CURS_HR_NECE FROM CURSO WHERE CURS_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Curso();
                obj.setCodigo(rs.getInt("CURS_ID"));
                obj.setDescricao(rs.getString("CURS_DESC"));
                obj.setCargaHoraria(rs.getInt("CURS_HR_NECE"));
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
