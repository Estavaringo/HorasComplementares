/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoUsuario;
import Bean.Usuario;
import DAO.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aluno
 */
public class LoginServlet implements LogicaDeNegocio {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse response) {
        String login = req.getParameter("login");
        String senha = "";

        try {
            senha = new Criptografia().Digest(req.getParameter("senha"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            System.err.println("Erro ao criptografar a senha. Detalhes: " + ex.getMessage());
        }

//        Usuario usuario = new Usuario();
//
//        try {
//            usuario = new UsuarioDAO().LoginServlet(login);
//
//            if (usuario == null) {
//                HttpSession session = req.getSession();
//                session.setAttribute("usuarioInvalido", true);
//                return "index.jsp";
//            } else if (!senha.equals(usuario.getSenha())) {
//                HttpSession session = req.getSession();
//                session.setAttribute("senhaInvalida", true);
//                return "index.jsp";
//            } else if (!usuario.isAtivo()) {
//                HttpSession session = req.getSession();
//                session.setAttribute("senhaInvalida", true);
//                return "index.jsp";
//            } else {
//                HttpSession session = req.getSession();
//                session.removeAttribute("senhaInvalida");
//                session.removeAttribute("usuarioInvalido");
//                session.setAttribute("usuarioLogado", usuario);
//                return "/WEB-INF/Paginas/dashboard.jsp";
//            }
//
//        } catch (SQLException ex) {
//            System.err.println("Erro ao consultar usuário no banco de dados. Detalhes: " + ex.getMessage());
//            return "erro.html";
//        }
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuario = new TipoUsuario();
        usuario.setLogin(login);
        usuario.setLogin(senha);
        tipoUsuario.setDescricao("admin");
        usuario.setTipoUsuario(tipoUsuario);

        HttpSession session = req.getSession();
        session.removeAttribute("senhaInvalida");
        session.removeAttribute("usuarioInvalido");
        session.setAttribute("usuarioLogado", usuario);
        session.setAttribute("logicaDeNegocio", "CursoServlet");
        session.setAttribute("tarefa", "consultarLista");

        
        return "/WEB-INF/Paginas/cursos.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
