/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.StatusRelatorioAtividade;
import DAO.StatusRelatorioAtividadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class StatusRelatorioAtividadeServlet implements LogicaDeNegocio{

    //Declarações
    private StatusRelatorioAtividade statusRelatorioAtividade = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova statusRelatorioAtividade
                    statusRelatorioAtividade = new StatusRelatorioAtividade();

                    //Atribui as informações da statusRelatorioAtividade no objeto
                    statusRelatorioAtividade.setDescricao(req.getParameter("descricao"));

                    //Grava um nova statusRelatorioAtividade no banco de dados
                    new StatusRelatorioAtividadeDAO().Incluir(statusRelatorioAtividade);

                    //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoStatusRelatorioAtividade", statusRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova statusRelatorioAtividade
                    statusRelatorioAtividade = new StatusRelatorioAtividade();

                    //Atribui as informações da statusRelatorioAtividade no objeto
                    statusRelatorioAtividade.setDescricao(req.getParameter("descricao"));
                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui statusRelatorioAtividade no banco de dados
                    new StatusRelatorioAtividadeDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoStatusRelatorioAtividade", statusRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova statusRelatorioAtividade
                    statusRelatorioAtividade = new StatusRelatorioAtividade();

                    //Atribui as informações da statusRelatorioAtividade no objeto
                    statusRelatorioAtividade.setDescricao(req.getParameter("descricao"));
                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera statusRelatorioAtividade no banco de dados
                    new StatusRelatorioAtividadeDAO().Alterar(statusRelatorioAtividade);

                    //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoStatusRelatorioAtividade", statusRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova statusRelatorioAtividade
                    statusRelatorioAtividade = new StatusRelatorioAtividade();

                    //Grava um nova statusRelatorioAtividade no banco de dados
                    statusRelatorioAtividade = new StatusRelatorioAtividadeDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaStatusRelatorioAtividade", statusRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<StatusRelatorioAtividade> listaStatusRelatorioAtividade = new ArrayList<>();

                    //Grava um nova statusRelatorioAtividade no banco de dados
                    listaStatusRelatorioAtividade = new StatusRelatorioAtividadeDAO().Consultar();

                    //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaStatusRelatorioAtividade", listaStatusRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/statusAtividade.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<StatusRelatorioAtividade> listaStatusRelatorioAtividade = new ArrayList<>();

            //Grava um nova statusRelatorioAtividade no banco de dados
            listaStatusRelatorioAtividade = new StatusRelatorioAtividadeDAO().Consultar();

            //Atribui a ultima statusRelatorioAtividade como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaStatusRelatorioAtividade", listaStatusRelatorioAtividade);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar status de relatorio atividade no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/statusAtividade.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
