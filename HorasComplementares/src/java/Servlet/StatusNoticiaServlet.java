/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.StatusNoticia;
import DAO.StatusNoticiaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class StatusNoticiaServlet implements LogicaDeNegocio{

    //Declarações
    private StatusNoticia statusNoticia = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova statusNoticia
                    statusNoticia = new StatusNoticia();

                    //Atribui as informações da statusNoticia no objeto
                    statusNoticia.setDescricao(req.getParameter("descricao"));

                    //Grava um nova statusNoticia no banco de dados
                    new StatusNoticiaDAO().Incluir(statusNoticia);

                    //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoStatusNoticia", statusNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir status de noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova statusNoticia
                    statusNoticia = new StatusNoticia();

                    //Atribui as informações da statusNoticia no objeto
                    statusNoticia.setDescricao(req.getParameter("descricao"));
                    statusNoticia.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui statusNoticia no banco de dados
                    new StatusNoticiaDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoStatusNoticia", statusNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover status de noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova statusNoticia
                    statusNoticia = new StatusNoticia();

                    //Atribui as informações da statusNoticia no objeto
                    statusNoticia.setDescricao(req.getParameter("descricao"));
                    statusNoticia.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera statusNoticia no banco de dados
                    new StatusNoticiaDAO().Alterar(statusNoticia);

                    //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoStatusNoticia", statusNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar status de noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova statusNoticia
                    statusNoticia = new StatusNoticia();

                    //Grava um nova statusNoticia no banco de dados
                    statusNoticia = new StatusNoticiaDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaStatusNoticia", statusNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar status de noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<StatusNoticia> listaStatusNoticia = new ArrayList<>();

                    //Grava um nova statusNoticia no banco de dados
                    listaStatusNoticia = new StatusNoticiaDAO().Consultar();

                    //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaStatusNoticia", listaStatusNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar status de noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipocontato.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<StatusNoticia> listaStatusNoticia = new ArrayList<>();

            //Grava um nova statusNoticia no banco de dados
            listaStatusNoticia = new StatusNoticiaDAO().Consultar();

            //Atribui a ultima statusNoticia como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaStatusNoticia", listaStatusNoticia);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar status de noticia no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipocontato.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
