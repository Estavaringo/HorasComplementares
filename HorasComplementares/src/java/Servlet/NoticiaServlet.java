/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Noticia;
import Bean.StatusNoticia;
import Bean.Usuario;
import DAO.NoticiaDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class NoticiaServlet implements LogicaDeNegocio{

    //Declarações
    private Noticia noticia = null;
    private Usuario usuario = new Usuario();
    private StatusNoticia statusNoticia = new StatusNoticia();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova noticia
                    noticia = new Noticia();

                    //Atribui as informações da noticia no objeto
                    noticia.setTitulo(req.getParameter("titulo"));
                    noticia.setDescricao(req.getParameter("descricao"));
                    noticia.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));
                    
                    java.util.Date dataAgendamento = formato.parse(req.getParameter("dataAgendamento"));
                    Date sqlAgendamento = new Date(dataAgendamento.getTime());
                    noticia.setDataAgendamento(sqlAgendamento);
                    
                    java.util.Date dataPublicacao = formato.parse(req.getParameter("dataPublicacao"));
                    Date sqlPublicacao = new Date(dataPublicacao.getTime());
                    noticia.setDataPublicacao(sqlPublicacao);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    statusNoticia.setCodigo(Integer.parseInt(req.getParameter("codigoStatusNoticia")));
                    
                    noticia.setUsuario(usuario);
                    noticia.setStatusNoticia(statusNoticia);
                    
                    //Grava um nova noticia no banco de dados
                    new NoticiaDAO().Incluir(noticia);

                    //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoNoticia", noticia);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova noticia
                    noticia = new Noticia();

                    //Atribui as informações da noticia no objeto
                    noticia.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    noticia.setTitulo(req.getParameter("titulo"));
                    noticia.setDescricao(req.getParameter("descricao"));
                    noticia.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));
                    
                    java.util.Date dataAgendamento = formato.parse(req.getParameter("dataAgendamento"));
                    Date sqlAgendamento = new Date(dataAgendamento.getTime());
                    noticia.setDataAgendamento(sqlAgendamento);
                    
                    java.util.Date dataPublicacao = formato.parse(req.getParameter("dataPublicacao"));
                    Date sqlPublicacao = new Date(dataPublicacao.getTime());
                    noticia.setDataPublicacao(sqlPublicacao);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    statusNoticia.setCodigo(Integer.parseInt(req.getParameter("codigoStatusNoticia")));
                    
                    noticia.setUsuario(usuario);
                    noticia.setStatusNoticia(statusNoticia);
                    
                    //Exclui noticia no banco de dados
                    new NoticiaDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoNoticia", noticia);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova noticia
                    noticia = new Noticia();

                    //Atribui as informações da noticia no objeto
                    noticia.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    noticia.setTitulo(req.getParameter("titulo"));
                    noticia.setDescricao(req.getParameter("descricao"));
                    noticia.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));
                    
                    java.util.Date dataAgendamento = formato.parse(req.getParameter("dataAgendamento"));
                    Date sqlAgendamento = new Date(dataAgendamento.getTime());
                    noticia.setDataAgendamento(sqlAgendamento);
                    
                    java.util.Date dataPublicacao = formato.parse(req.getParameter("dataPublicacao"));
                    Date sqlPublicacao = new Date(dataPublicacao.getTime());
                    noticia.setDataPublicacao(sqlPublicacao);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    statusNoticia.setCodigo(Integer.parseInt(req.getParameter("codigoStatusNoticia")));
                    
                    noticia.setUsuario(usuario);
                    noticia.setStatusNoticia(statusNoticia);
                    
                    //altera noticia no banco de dados
                    new NoticiaDAO().Alterar(noticia);

                    //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoNoticia", noticia);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova noticia
                    noticia = new Noticia();

                    //Grava um nova noticia no banco de dados
                    noticia = new NoticiaDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaNoticia", noticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<Noticia> listaNoticia = new ArrayList<>();

                    //Grava um nova noticia no banco de dados
                    listaNoticia = new NoticiaDAO().Consultar();

                    //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaNoticia", listaNoticia);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar noticia no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tiporelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<Noticia> listaNoticia = new ArrayList<>();

            //Grava um nova noticia no banco de dados
            listaNoticia = new NoticiaDAO().Consultar();

            //Atribui a ultima noticia como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaNoticia", listaNoticia);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar noticia no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tiporelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
