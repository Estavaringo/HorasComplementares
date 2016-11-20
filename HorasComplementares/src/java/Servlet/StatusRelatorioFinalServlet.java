/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.StatusRelatorioFinal;
import DAO.StatusRelatorioFinalDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class StatusRelatorioFinalServlet implements LogicaDeNegocio{

    //Declarações
    private StatusRelatorioFinal statusRelatorioFinal = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova statusRelatorioFinal
                    statusRelatorioFinal = new StatusRelatorioFinal();

                    //Atribui as informações da statusRelatorioFinal no objeto
                    statusRelatorioFinal.setDescricao(req.getParameter("descricao"));

                    //Grava um nova statusRelatorioFinal no banco de dados
                    new StatusRelatorioFinalDAO().Incluir(statusRelatorioFinal);

                    //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoStatusRelatorioFinal", statusRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova statusRelatorioFinal
                    statusRelatorioFinal = new StatusRelatorioFinal();

                    //Atribui as informações da statusRelatorioFinal no objeto
                    statusRelatorioFinal.setDescricao(req.getParameter("descricao"));
                    statusRelatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui statusRelatorioFinal no banco de dados
                    new StatusRelatorioFinalDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoStatusRelatorioFinal", statusRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova statusRelatorioFinal
                    statusRelatorioFinal = new StatusRelatorioFinal();

                    //Atribui as informações da statusRelatorioFinal no objeto
                    statusRelatorioFinal.setDescricao(req.getParameter("descricao"));
                    statusRelatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera statusRelatorioFinal no banco de dados
                    new StatusRelatorioFinalDAO().Alterar(statusRelatorioFinal);

                    //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoStatusRelatorioFinal", statusRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova statusRelatorioFinal
                    statusRelatorioFinal = new StatusRelatorioFinal();

                    //Grava um nova statusRelatorioFinal no banco de dados
                    statusRelatorioFinal = new StatusRelatorioFinalDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaStatusRelatorioFinal", statusRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<StatusRelatorioFinal> listaStatusRelatorioFinal = new ArrayList<>();

                    //Grava um nova statusRelatorioFinal no banco de dados
                    listaStatusRelatorioFinal = new StatusRelatorioFinalDAO().Consultar();

                    //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaStatusRelatorioFinal", listaStatusRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipocontato.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<StatusRelatorioFinal> listaStatusRelatorioFinal = new ArrayList<>();

            //Grava um nova statusRelatorioFinal no banco de dados
            listaStatusRelatorioFinal = new StatusRelatorioFinalDAO().Consultar();

            //Atribui a ultima statusRelatorioFinal como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaStatusRelatorioFinal", listaStatusRelatorioFinal);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipocontato.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
