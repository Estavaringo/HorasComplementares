/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Documento;
import Bean.Usuario;
import DAO.DocumentoDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class DocumentoServlet implements LogicaDeNegocio {

    //Declarações
    private Documento documento = null;
    private Usuario usuario = new Usuario();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova documento
                    documento = new Documento();

                    //Atribui as informações da documento no objeto
                    documento.setDescricao(req.getParameter("descricao"));
                    documento.setUrl(req.getParameter("url"));
                    documento.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));

                    java.util.Date date = formato.parse(req.getParameter("data"));
                    Date sql = new Date(date.getTime());
                    documento.setData(sql);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    
                    documento.setUsuario(usuario);

                    //Grava um nova documento no banco de dados
                    new DocumentoDAO().Incluir(documento);

                    //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoDocumento", documento);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova documento
                    documento = new Documento();

                    //Atribui as informações da documento no objeto
                    documento.setDescricao(req.getParameter("descricao"));
                    documento.setUrl(req.getParameter("url"));
                    documento.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));

                    java.util.Date date = formato.parse(req.getParameter("data"));
                    Date sql = new Date(date.getTime());
                    documento.setData(sql);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    
                    documento.setUsuario(usuario);

                    //Exclui documento no banco de dados
                    new DocumentoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoDocumento", documento);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova documento
                    documento = new Documento();

                    //Atribui as informações da documento no objeto
                    documento.setDescricao(req.getParameter("descricao"));
                    documento.setUrl(req.getParameter("url"));
                    documento.setVisivel(Boolean.parseBoolean(req.getParameter("visivel")));

                    java.util.Date date = formato.parse(req.getParameter("data"));
                    Date sql = new Date(date.getTime());
                    documento.setData(sql);
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    
                    documento.setUsuario(usuario);

                    //altera documento no banco de dados
                    new DocumentoDAO().Alterar(documento);

                    //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoDocumento", documento);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova documento
                    documento = new Documento();

                    //Grava um nova documento no banco de dados
                    documento = new DocumentoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaDocumento", documento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<Documento> listaDocumento = new ArrayList<>();

                    //Grava um nova documento no banco de dados
                    listaDocumento = new DocumentoDAO().Consultar();

                    //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaDocumento", listaDocumento);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar documento no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tiporelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<Documento> listaDocumento = new ArrayList<>();

            //Grava um nova documento no banco de dados
            listaDocumento = new DocumentoDAO().Consultar();

            //Atribui a ultima documento como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaDocumento", listaDocumento);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar documento no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tiporelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
