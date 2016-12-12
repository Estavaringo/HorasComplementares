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
                    = "INSERT INTO CURSO (CURS_NOME, CURS_DESC) VALUES (?,?)";
            PreparedStatement p = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getNome());
            p.setString(2, obj.getDescricao());
            p.execute();       
            p.close();
            bd.desconectar();
        } catch (SQLException ex) {
            bd.desconectar();
            throw ex;
        }    
    }
    
    public int IncluirComRetornoDoCodigo(Curso obj) throws SQLException {
        try {
            
            //Declaração
            int codigoGerado = 0;
            
            //Conecta ao banco de dados
            bd.conectar();
            
            //Faz inserção do novo curso
            String strSql
                    = "INSERT INTO CURSO (CURS_NOME, CURS_DESC) VALUES (?,?)";
            PreparedStatement p = bd.connection.prepareStatement(strSql,Statement.RETURN_GENERATED_KEYS);
            p.setString(1, obj.getNome());
            p.setString(2, obj.getDescricao());
            p.execute();
            
            //Pega o código gerado na inserção
            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                codigoGerado = rs.getInt(1);
            }
            
            //Fecha a conexão com o banco de dados
            p.close();
            bd.desconectar();
            
            //Retorna o código do Curso gerado na inserção
            return codigoGerado;
            
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
                    = "UPDATE CURSO SET CURS_NOME = ?, CURS_DESC = ? WHERE CURS_ID = ?";
            PreparedStatement p
                    = bd.connection.prepareStatement(strSql);
            p.setString(1, obj.getNome());
            p.setString(2, obj.getDescricao());
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
            ResultSet rs = comando.executeQuery("SELECT CURS_ID, CURS_NOME, CURS_DESC FROM CURSO");
            while (rs.next()) {
                Curso obj = new Curso();
                obj.setCodigo(rs.getInt("CURS_ID"));
                obj.setNome(rs.getString("CURS_NOME"));
                obj.setDescricao(rs.getString("CURS_DESC"));
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
            String strSQL = "SELECT CURS_ID, CURS_NOME, CURS_DESC FROM CURSO WHERE CURS_ID = ?";
            PreparedStatement p = bd.connection.prepareStatement(strSQL);
            p.setInt(1, codigo);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                obj = new Curso();
                obj.setCodigo(rs.getInt("CURS_ID"));
                obj.setNome(rs.getString("CURS_NOME"));
                obj.setDescricao(rs.getString("CURS_DESC"));
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
