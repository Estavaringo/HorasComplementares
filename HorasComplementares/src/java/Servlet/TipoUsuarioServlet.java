/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.TipoUsuario;
import DAO.TipoUsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class TipoUsuarioServlet implements LogicaDeNegocio {

    //Declarações
    private TipoUsuario tipoUsuario = null;
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova tipoUsuario
                    tipoUsuario = new TipoUsuario();

                    //Atribui as informações da tipoUsuario no objeto
                    tipoUsuario.setDescricao(req.getParameter("descricao"));

                    //Grava um nova tipoUsuario no banco de dados
                    new TipoUsuarioDAO().Incluir(tipoUsuario);

                    //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoTipoUsuario", tipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova tipoUsuario
                    tipoUsuario = new TipoUsuario();

                    //Atribui as informações da tipoUsuario no objeto
                    tipoUsuario.setDescricao(req.getParameter("descricao"));
                    tipoUsuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //Exclui tipoUsuario no banco de dados
                    new TipoUsuarioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoTipoUsuario", tipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova tipoUsuario
                    tipoUsuario = new TipoUsuario();

                    //Atribui as informações da tipoUsuario no objeto
                    tipoUsuario.setDescricao(req.getParameter("descricao"));
                    tipoUsuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    //altera tipoUsuario no banco de dados
                    new TipoUsuarioDAO().Alterar(tipoUsuario);

                    //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoTipoUsuario", tipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova tipoUsuario
                    tipoUsuario = new TipoUsuario();

                    //Grava um nova tipoUsuario no banco de dados
                    tipoUsuario = new TipoUsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaTipoUsuario", tipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<TipoUsuario> listaTipoUsuario = new ArrayList<>();

                    //Grava um nova tipoUsuario no banco de dados
                    listaTipoUsuario = new TipoUsuarioDAO().Consultar();

                    //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoUsuario", listaTipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tipousuario.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<TipoUsuario> listaTipoUsuario = new ArrayList<>();

            //Grava um nova tipoUsuario no banco de dados
            listaTipoUsuario = new TipoUsuarioDAO().Consultar();

            //Atribui a ultima tipoUsuario como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaTipoUsuario", listaTipoUsuario);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar tipo de usuario no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tipousuario.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
