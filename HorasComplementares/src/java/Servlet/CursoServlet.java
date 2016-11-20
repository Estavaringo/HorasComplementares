/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Curso;
import DAO.CursoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class CursoServlet implements LogicaDeNegocio{

    //Declarações
    private Curso curso = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova curso
                    curso = new Curso();

                    //Atribui as informações da curso no objeto
                    curso.setDescricao(req.getParameter("descricao"));
                    curso.setCargaHoraria(Integer.parseInt(req.getParameter("cargaHoraria")));

                    //Grava um nova curso no banco de dados
                    new CursoDAO().Incluir(curso);

                    //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoCurso", curso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova curso
                    curso = new Curso();

                    //Atribui as informações da curso no objeto
                    curso.setDescricao(req.getParameter("descricao"));
                    curso.setCargaHoraria(Integer.parseInt(req.getParameter("cargaHoraria")));
                    curso.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui curso no banco de dados
                    new CursoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoCurso", curso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova curso
                    curso = new Curso();

                    //Atribui as informações da curso no objeto
                    curso.setDescricao(req.getParameter("descricao"));
                    curso.setCargaHoraria(Integer.parseInt(req.getParameter("cargaHoraria")));
                    curso.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera curso no banco de dados
                    new CursoDAO().Alterar(curso);

                    //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoCurso", curso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova curso
                    curso = new Curso();

                    //Grava um nova curso no banco de dados
                    curso = new CursoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaCurso", curso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<Curso> listaCurso = new ArrayList<>();

                    //Grava um nova curso no banco de dados
                    listaCurso = new CursoDAO().Consultar();

                    //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaCurso", listaCurso);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tiporelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<Curso> listaCurso = new ArrayList<>();

            //Grava um nova curso no banco de dados
            listaCurso = new CursoDAO().Consultar();

            //Atribui a ultima curso como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaCurso", listaCurso);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar curso no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tiporelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
