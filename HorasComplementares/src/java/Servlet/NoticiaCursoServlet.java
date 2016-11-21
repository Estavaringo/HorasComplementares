/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.NoticiaCurso;
import Bean.Curso;
import Bean.Noticia;
import DAO.NoticiaCursoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class NoticiaCursoServlet implements LogicaDeNegocio {

    //Declarações
    private NoticiaCurso noticiaCurso = null;
    private Curso curso = new Curso();
    private Noticia noticia = new Noticia();
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova noticiaCurso
                    noticiaCurso = new NoticiaCurso();

                    //Atribui as informações da noticiaCurso no objeto
                    noticiaCurso.setDescricao(req.getParameter("descricao"));

                    curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                    noticia.setCodigo(Integer.parseInt(req.getParameter("codigoNoticia")));

                    noticiaCurso.setCurso(curso);
                    noticiaCurso.setNoticia(noticia);

                    //Grava um nova noticiaCurso no banco de dados
                    new NoticiaCursoDAO().Incluir(noticiaCurso);

                    //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoNoticiaCurso", noticiaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova noticiaCurso
                    noticiaCurso = new NoticiaCurso();

                    //Atribui as informações da noticiaCurso no objeto
                    noticiaCurso.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    noticiaCurso.setDescricao(req.getParameter("descricao"));

                    curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                    noticia.setCodigo(Integer.parseInt(req.getParameter("codigoNoticia")));

                    noticiaCurso.setCurso(curso);
                    noticiaCurso.setNoticia(noticia);

                    //Exclui noticiaCurso no banco de dados
                    new NoticiaCursoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoNoticiaCurso", noticiaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova noticiaCurso
                    noticiaCurso = new NoticiaCurso();

                    //Atribui as informações da noticiaCurso no objeto
                    noticiaCurso.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    noticiaCurso.setDescricao(req.getParameter("descricao"));

                    curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                    noticia.setCodigo(Integer.parseInt(req.getParameter("codigoNoticia")));

                    noticiaCurso.setCurso(curso);
                    noticiaCurso.setNoticia(noticia);

                    //altera noticiaCurso no banco de dados
                    new NoticiaCursoDAO().Alterar(noticiaCurso);

                    //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoNoticiaCurso", noticiaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova noticiaCurso
                    noticiaCurso = new NoticiaCurso();

                    //Grava um nova noticiaCurso no banco de dados
                    noticiaCurso = new NoticiaCursoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaNoticiaCurso", noticiaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<NoticiaCurso> listaNoticiaCurso = new ArrayList<>();

                    //Grava um nova noticiaCurso no banco de dados
                    listaNoticiaCurso = new NoticiaCursoDAO().Consultar();

                    //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaNoticiaCurso", listaNoticiaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/index.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<NoticiaCurso> listaNoticiaCurso = new ArrayList<>();

            //Grava um nova noticiaCurso no banco de dados
            listaNoticiaCurso = new NoticiaCursoDAO().Consultar();

            //Atribui a ultima noticiaCurso como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaNoticiaCurso", listaNoticiaCurso);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar noticiaCurso no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/index.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
