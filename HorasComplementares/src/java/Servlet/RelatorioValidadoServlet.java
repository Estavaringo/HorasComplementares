/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.RelatorioValidado;
import Bean.RelatorioAtividade;
import Bean.Usuario;
import DAO.RelatorioValidadoDAO;
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
public class RelatorioValidadoServlet implements LogicaDeNegocio {

    //Declarações
    private RelatorioValidado relatorioValidado = null;
    private Usuario usuario = new Usuario();
    private RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova relatorioValidado
                    relatorioValidado = new RelatorioValidado();

                    //Atribui as informações da relatorioValidado no objeto
                    relatorioValidado.setComentario(req.getParameter("descricao"));
                    relatorioValidado.setHorasValidadas(Integer.parseInt(req.getParameter("horas")));

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioValidado.setDataValidacao(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));

                    relatorioValidado.setUsuario(usuario);
                    relatorioValidado.setRelatorioAtividade(relatorioAtividade);

                    //Grava um nova relatorioValidado no banco de dados
                    new RelatorioValidadoDAO().Incluir(relatorioValidado);

                    //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoRelatorioValidado", relatorioValidado);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova relatorioValidado
                    relatorioValidado = new RelatorioValidado();

                    //Atribui as informações da relatorioValidado no objeto
                    relatorioValidado.setComentario(req.getParameter("descricao"));
                    relatorioValidado.setHorasValidadas(Integer.parseInt(req.getParameter("horas")));

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioValidado.setDataValidacao(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));

                    relatorioValidado.setUsuario(usuario);
                    relatorioValidado.setRelatorioAtividade(relatorioAtividade);

                    //Exclui relatorioValidado no banco de dados
                    new RelatorioValidadoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoRelatorioValidado", relatorioValidado);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova relatorioValidado
                    relatorioValidado = new RelatorioValidado();

                    //Atribui as informações da relatorioValidado no objeto
                    relatorioValidado.setComentario(req.getParameter("descricao"));
                    relatorioValidado.setHorasValidadas(Integer.parseInt(req.getParameter("horas")));

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioValidado.setDataValidacao(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));

                    relatorioValidado.setUsuario(usuario);
                    relatorioValidado.setRelatorioAtividade(relatorioAtividade);

                    //altera relatorioValidado no banco de dados
                    new RelatorioValidadoDAO().Alterar(relatorioValidado);

                    //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoRelatorioValidado", relatorioValidado);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova relatorioValidado
                    relatorioValidado = new RelatorioValidado();

                    //Grava um nova relatorioValidado no banco de dados
                    relatorioValidado = new RelatorioValidadoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaRelatorioValidado", relatorioValidado);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<RelatorioValidado> listaRelatorioValidado = new ArrayList<>();

                    //Grava um nova relatorioValidado no banco de dados
                    listaRelatorioValidado = new RelatorioValidadoDAO().Consultar();

                    //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaRelatorioValidado", listaRelatorioValidado);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/index.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<RelatorioValidado> listaRelatorioValidado = new ArrayList<>();

            //Grava um nova relatorioValidado no banco de dados
            listaRelatorioValidado = new RelatorioValidadoDAO().Consultar();

            //Atribui a ultima relatorioValidado como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaRelatorioValidado", listaRelatorioValidado);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar relatorioValidado no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/index.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
