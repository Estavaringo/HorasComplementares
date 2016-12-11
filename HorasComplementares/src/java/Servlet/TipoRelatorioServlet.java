/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoRelatorio;
import DAO.TipoRelatorioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class TipoRelatorioServlet implements LogicaDeNegocio{

    //Declarações
    private TipoRelatorio tipoRelatorio = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoRelatorio
                    tipoRelatorio = new TipoRelatorio();

                    //Atribui as informações da tipoRelatorio no objeto
                    tipoRelatorio.setNome(req.getParameter("nome"));
                    tipoRelatorio.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoRelatorio no banco de dados
                    new TipoRelatorioDAO().Incluir(tipoRelatorio);

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoRelatorio", tipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoRelatorio
                    tipoRelatorio = new TipoRelatorio();

                    //Atribui as informações da tipoRelatorio no objeto
                    tipoRelatorio.setNome(req.getParameter("nome"));
                    tipoRelatorio.setDescricao(req.getParameter("descricao"));
                    tipoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoRelatorio no banco de dados
                    new TipoRelatorioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoRelatorio", tipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoRelatorio
                    tipoRelatorio = new TipoRelatorio();

                    //Atribui as informações da tipoRelatorio no objeto
                    tipoRelatorio.setNome(req.getParameter("nome"));
                    tipoRelatorio.setDescricao(req.getParameter("descricao"));
                    tipoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoRelatorio no banco de dados
                    new TipoRelatorioDAO().Alterar(tipoRelatorio);

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoRelatorio", tipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoRelatorio
                    tipoRelatorio = new TipoRelatorio();

                    //Grava um nova tipoRelatorio no banco de dados
                    tipoRelatorio = new TipoRelatorioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoRelatorio", tipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoRelatorio> listaTipoRelatorio = new ArrayList<>();

                    //Grava um nova tipoRelatorio no banco de dados
                    listaTipoRelatorio = new TipoRelatorioDAO().Consultar();

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoRelatorio", listaTipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipoRelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoRelatorio> listaTipoRelatorio = new ArrayList<>();

            //Grava um nova tipoRelatorio no banco de dados
            listaTipoRelatorio = new TipoRelatorioDAO().Consultar();

            //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoRelatorio", listaTipoRelatorio);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de relatorio no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipoRelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
