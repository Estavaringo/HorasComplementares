/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoNotificacao;
import DAO.TipoNotificacaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class TipoNotificacaoServlet implements LogicaDeNegocio{

    //Declarações
    private TipoNotificacao tipoNotificacao = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoNotificacao
                    tipoNotificacao = new TipoNotificacao();

                    //Atribui as informações da tipoNotificacao no objeto
                    tipoNotificacao.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoNotificacao no banco de dados
                    new TipoNotificacaoDAO().Incluir(tipoNotificacao);

                    //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoNotificacao", tipoNotificacao);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoNotificacao
                    tipoNotificacao = new TipoNotificacao();

                    //Atribui as informações da tipoNotificacao no objeto
                    tipoNotificacao.setDescricao(req.getParameter("descricao"));
                    tipoNotificacao.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoNotificacao no banco de dados
                    new TipoNotificacaoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoNotificacao", tipoNotificacao);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoNotificacao
                    tipoNotificacao = new TipoNotificacao();

                    //Atribui as informações da tipoNotificacao no objeto
                    tipoNotificacao.setDescricao(req.getParameter("descricao"));
                    tipoNotificacao.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoNotificacao no banco de dados
                    new TipoNotificacaoDAO().Alterar(tipoNotificacao);

                    //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoNotificacao", tipoNotificacao);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoNotificacao
                    tipoNotificacao = new TipoNotificacao();

                    //Grava um nova tipoNotificacao no banco de dados
                    tipoNotificacao = new TipoNotificacaoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoNotificacao", tipoNotificacao);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoNotificacao> listaTipoNotificacao = new ArrayList<>();

                    //Grava um nova tipoNotificacao no banco de dados
                    listaTipoNotificacao = new TipoNotificacaoDAO().Consultar();

                    //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoNotificacao", listaTipoNotificacao);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipoNotificacao.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoNotificacao> listaTipoNotificacao = new ArrayList<>();

            //Grava um nova tipoNotificacao no banco de dados
            listaTipoNotificacao = new TipoNotificacaoDAO().Consultar();

            //Atribui a ultima tipoNotificacao como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoNotificacao", listaTipoNotificacao);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de notificacao no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipoNotificacao.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
