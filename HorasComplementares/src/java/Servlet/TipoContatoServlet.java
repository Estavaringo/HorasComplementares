/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoContato;
import DAO.TipoContatoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class TipoContatoServlet implements LogicaDeNegocio{

    //Declarações
    private TipoContato tipoContato = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoContato
                    tipoContato = new TipoContato();

                    //Atribui as informações da tipoContato no objeto
                    tipoContato.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoContato no banco de dados
                    new TipoContatoDAO().Incluir(tipoContato);

                    //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoContato", tipoContato);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoContato
                    tipoContato = new TipoContato();

                    //Atribui as informações da tipoContato no objeto
                    tipoContato.setDescricao(req.getParameter("descricao"));
                    tipoContato.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoContato no banco de dados
                    new TipoContatoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoContato", tipoContato);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoContato
                    tipoContato = new TipoContato();

                    //Atribui as informações da tipoContato no objeto
                    tipoContato.setDescricao(req.getParameter("descricao"));
                    tipoContato.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoContato no banco de dados
                    new TipoContatoDAO().Alterar(tipoContato);

                    //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoContato", tipoContato);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoContato
                    tipoContato = new TipoContato();

                    //Grava um nova tipoContato no banco de dados
                    tipoContato = new TipoContatoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoContato", tipoContato);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoContato> listaTipoContato = new ArrayList<>();

                    //Grava um nova tipoContato no banco de dados
                    listaTipoContato = new TipoContatoDAO().Consultar();

                    //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoContato", listaTipoContato);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipocontato.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoContato> listaTipoContato = new ArrayList<>();

            //Grava um nova tipoContato no banco de dados
            listaTipoContato = new TipoContatoDAO().Consultar();

            //Atribui a ultima tipoContato como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoContato", listaTipoContato);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de contato no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipocontato.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
