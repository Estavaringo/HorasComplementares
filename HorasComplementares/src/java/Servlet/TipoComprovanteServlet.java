/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.TipoComprovante;
import DAO.TipoComprovanteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class TipoComprovanteServlet implements LogicaDeNegocio{

    //Declarações
    private TipoComprovante tipoComprovante = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoComprovante
                    tipoComprovante = new TipoComprovante();

                    //Atribui as informações da tipoComprovante no objeto
                    tipoComprovante.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoComprovante no banco de dados
                    new TipoComprovanteDAO().Incluir(tipoComprovante);

                    //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoDocumento", tipoComprovante);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoComprovante
                    tipoComprovante = new TipoComprovante();

                    //Atribui as informações da tipoComprovante no objeto
                    tipoComprovante.setDescricao(req.getParameter("descricao"));
                    tipoComprovante.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoComprovante no banco de dados
                    new TipoComprovanteDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoDocumento", tipoComprovante);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoComprovante
                    tipoComprovante = new TipoComprovante();

                    //Atribui as informações da tipoComprovante no objeto
                    tipoComprovante.setDescricao(req.getParameter("descricao"));
                    tipoComprovante.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoComprovante no banco de dados
                    new TipoComprovanteDAO().Alterar(tipoComprovante);

                    //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoDocumento", tipoComprovante);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoComprovante
                    tipoComprovante = new TipoComprovante();

                    //Grava um nova tipoComprovante no banco de dados
                    tipoComprovante = new TipoComprovanteDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoDocumento", tipoComprovante);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoComprovante> listaTipoDocumento = new ArrayList<>();

                    //Grava um nova tipoComprovante no banco de dados
                    listaTipoDocumento = new TipoComprovanteDAO().Consultar();

                    //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoDocumento", listaTipoDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipoComprovante.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoComprovante> listaTipoDocumento = new ArrayList<>();

            //Grava um nova tipoComprovante no banco de dados
            listaTipoDocumento = new TipoComprovanteDAO().Consultar();

            //Atribui a ultima tipoComprovante como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoDocumento", listaTipoDocumento);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de documento no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipoComprovante.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
