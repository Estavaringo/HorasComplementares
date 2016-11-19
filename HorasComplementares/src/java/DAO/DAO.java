/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author flaviosampaioreisdelima
 */
public interface DAO <T> {
    public void Incluir(T obj) throws SQLException;
    public void Excluir(int codigo) throws SQLException;
    public void Alterar(T obj) throws SQLException;
    public ArrayList<T> Consultar() throws SQLException;
    public T Consultar(int codigo) throws SQLException;    
}
