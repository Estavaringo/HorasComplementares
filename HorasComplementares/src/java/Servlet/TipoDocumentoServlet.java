/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoDocumento;
import DAO.TipoDocumentoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class TipoDocumentoServlet implements LogicaDeNegocio{

    //Declarações
    private TipoDocumento tipoDocumento = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoDocumento
                    tipoDocumento = new TipoDocumento();

                    //Atribui as informações da tipoDocumento no objeto
                    tipoDocumento.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoDocumento no banco de dados
                    new TipoDocumentoDAO().Incluir(tipoDocumento);

                    //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoDocumento", tipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoDocumento
                    tipoDocumento = new TipoDocumento();

                    //Atribui as informações da tipoDocumento no objeto
                    tipoDocumento.setDescricao(req.getParameter("descricao"));
                    tipoDocumento.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoDocumento no banco de dados
                    new TipoDocumentoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoDocumento", tipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoDocumento
                    tipoDocumento = new TipoDocumento();

                    //Atribui as informações da tipoDocumento no objeto
                    tipoDocumento.setDescricao(req.getParameter("descricao"));
                    tipoDocumento.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoDocumento no banco de dados
                    new TipoDocumentoDAO().Alterar(tipoDocumento);

                    //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoDocumento", tipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoDocumento
                    tipoDocumento = new TipoDocumento();

                    //Grava um nova tipoDocumento no banco de dados
                    tipoDocumento = new TipoDocumentoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoDocumento", tipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoDocumento> listaTipoDocumento = new ArrayList<>();

                    //Grava um nova tipoDocumento no banco de dados
                    listaTipoDocumento = new TipoDocumentoDAO().Consultar();

                    //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoDocumento", listaTipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipoDocumento.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoDocumento> listaTipoDocumento = new ArrayList<>();

            //Grava um nova tipoDocumento no banco de dados
            listaTipoDocumento = new TipoDocumentoDAO().Consultar();

            //Atribui a ultima tipoDocumento como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoDocumento", listaTipoDocumento);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipoDocumento.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
