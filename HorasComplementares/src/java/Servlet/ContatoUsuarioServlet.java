/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.ContatoUsuario;
import Bean.TipoContato;
import Bean.Usuario;
import DAO.ContatoUsuarioDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
public class ContatoUsuarioServlet implements LogicaDeNegocio{

    //Declarações
    private ContatoUsuario contatoUsuario = null;
    private Usuario usuario = new Usuario();
    private TipoContato tipoContato = new TipoContato();
    private String tarefa;


    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova contatoUsuario
                    contatoUsuario = new ContatoUsuario();

                    //Atribui as informações da contatoUsuario no objeto
                    contatoUsuario.setDescricao(req.getParameter("descricao"));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoContato.setCodigo(Integer.parseInt(req.getParameter("codigoTipoContato")));
                    
                    contatoUsuario.setUsuario(usuario);
                    contatoUsuario.setTipoContato(tipoContato);
                    
                    //Grava um nova contatoUsuario no banco de dados
                    new ContatoUsuarioDAO().Incluir(contatoUsuario);

                    //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoContatoUsuario", contatoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova contatoUsuario
                    contatoUsuario = new ContatoUsuario();

                    //Atribui as informações da contatoUsuario no objeto
                    contatoUsuario.setCodigo(Integer.parseInt(req.getParameter("descricao")));
                    contatoUsuario.setDescricao(req.getParameter("descricao"));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoContato.setCodigo(Integer.parseInt(req.getParameter("codigoTipoContato")));
                    
                    contatoUsuario.setUsuario(usuario);
                    contatoUsuario.setTipoContato(tipoContato);

                    //Exclui contatoUsuario no banco de dados
                    new ContatoUsuarioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoContatoUsuario", contatoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova contatoUsuario
                    contatoUsuario = new ContatoUsuario();

                    //Atribui as informações da contatoUsuario no objeto
                    contatoUsuario.setCodigo(Integer.parseInt(req.getParameter("descricao")));
                    contatoUsuario.setDescricao(req.getParameter("descricao"));
                    
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoContato.setCodigo(Integer.parseInt(req.getParameter("codigoTipoContato")));
                    
                    contatoUsuario.setUsuario(usuario);
                    contatoUsuario.setTipoContato(tipoContato);

                    //altera contatoUsuario no banco de dados
                    new ContatoUsuarioDAO().Alterar(contatoUsuario);

                    //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoContatoUsuario", contatoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova contatoUsuario
                    contatoUsuario = new ContatoUsuario();

                    //Grava um nova contatoUsuario no banco de dados
                    contatoUsuario = new ContatoUsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaContatoUsuario", contatoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<ContatoUsuario> listaContatoUsuario = new ArrayList<>();

                    //Grava um nova contatoUsuario no banco de dados
                    listaContatoUsuario = new ContatoUsuarioDAO().Consultar();

                    //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaContatoUsuario", listaContatoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/tiporelatorio.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<ContatoUsuario> listaContatoUsuario = new ArrayList<>();

            //Grava um nova contatoUsuario no banco de dados
            listaContatoUsuario = new ContatoUsuarioDAO().Consultar();

            //Atribui a ultima contatoUsuario como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaContatoUsuario", listaContatoUsuario);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar contatoUsuario no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/tiporelatorio.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

    
}
