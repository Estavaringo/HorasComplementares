/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
public class Logout implements LogicaDeNegocio {

    public String executa(HttpServletRequest req, HttpServletResponse response) {
        req.getSession().removeAttribute("usuarioLogado");
        return "index.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }
    
}
