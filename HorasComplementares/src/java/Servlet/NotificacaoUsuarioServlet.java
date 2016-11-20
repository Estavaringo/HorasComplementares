/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.NotificacaoUsuario;
import Bean.TipoNotificacao;
import Bean.Usuario;
import DAO.NotificacaoUsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class NotificacaoUsuarioServlet implements LogicaDeNegocio{

    //Declarações
    private NotificacaoUsuario notificacaoUsuario = null;
    private Usuario usuario = new Usuario();
    private TipoNotificacao tipoNotificacao = new TipoNotificacao();
    private String tarefa;


    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova notificacaoUsuario
                    notificacaoUsuario = new NotificacaoUsuario();

                    //Atribui as informações da notificacaoUsuario no objeto
                    notificacaoUsuario.setAtiva(Boolean.parseBoolean(req.getParameter("ativa")));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoNotificacao.setCodigo(Integer.parseInt(req.getParameter("codigoTipoNotificacao")));
                    
                    notificacaoUsuario.setUsuario(usuario);
                    notificacaoUsuario.setTipoNotificacao(tipoNotificacao);
                    
                    //Grava um nova notificacaoUsuario no banco de dados
                    new NotificacaoUsuarioDAO().Incluir(notificacaoUsuario);

                    //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoNotificacaoUsuario", notificacaoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova notificacaoUsuario
                    notificacaoUsuario = new NotificacaoUsuario();

                    //Atribui as informações da notificacaoUsuario no objeto
                    notificacaoUsuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    notificacaoUsuario.setAtiva(Boolean.parseBoolean(req.getParameter("ativa")));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoNotificacao.setCodigo(Integer.parseInt(req.getParameter("codigoTipoNotificacao")));
                    
                    notificacaoUsuario.setUsuario(usuario);
                    notificacaoUsuario.setTipoNotificacao(tipoNotificacao);

                    //Exclui notificacaoUsuario no banco de dados
                    new NotificacaoUsuarioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoNotificacaoUsuario", notificacaoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova notificacaoUsuario
                    notificacaoUsuario = new NotificacaoUsuario();

                    //Atribui as informações da notificacaoUsuario no objeto
                    notificacaoUsuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    notificacaoUsuario.setAtiva(Boolean.parseBoolean(req.getParameter("ativa")));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoNotificacao.setCodigo(Integer.parseInt(req.getParameter("codigoTipoNotificacao")));
                    
                    notificacaoUsuario.setUsuario(usuario);
                    notificacaoUsuario.setTipoNotificacao(tipoNotificacao);

                    //altera notificacaoUsuario no banco de dados
                    new NotificacaoUsuarioDAO().Alterar(notificacaoUsuario);

                    //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoNotificacaoUsuario", notificacaoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova notificacaoUsuario
                    notificacaoUsuario = new NotificacaoUsuario();

                    //Grava um nova notificacaoUsuario no banco de dados
                    notificacaoUsuario = new NotificacaoUsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaNotificacaoUsuario", notificacaoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<NotificacaoUsuario> listaNotificacaoUsuario = new ArrayList<>();

                    //Grava um nova notificacaoUsuario no banco de dados
                    listaNotificacaoUsuario = new NotificacaoUsuarioDAO().Consultar();

                    //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaNotificacaoUsuario", listaNotificacaoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tiporelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<NotificacaoUsuario> listaNotificacaoUsuario = new ArrayList<>();

            //Grava um nova notificacaoUsuario no banco de dados
            listaNotificacaoUsuario = new NotificacaoUsuarioDAO().Consultar();

            //Atribui a ultima notificacaoUsuario como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaNotificacaoUsuario", listaNotificacaoUsuario);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar notificacaoUsuario no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tiporelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
