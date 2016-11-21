/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.RelatorioFinal;
import Bean.StatusRelatorioFinal;
import Bean.Usuario;
import DAO.RelatorioFinalDAO;
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
public class RelatorioFinalServlet implements LogicaDeNegocio {

    //Declarações
    private RelatorioFinal relatorioFinal = null;
    private Usuario usuarioSolicitacao = new Usuario();
    private Usuario usuarioEmissao = new Usuario();
    private StatusRelatorioFinal statusRelatorioFinal = new StatusRelatorioFinal();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova relatorioFinal
                    relatorioFinal = new RelatorioFinal();

                    //Atribui as informações da relatorioFinal no objeto
                    relatorioFinal.setHoras(Integer.parseInt(req.getParameter("horas")));
                    
                    java.util.Date dataSolicitacao = formato.parse(req.getParameter("dataSolicitacao"));
                    Date sqlSolicitacao = new Date(dataSolicitacao.getTime());
                    relatorioFinal.setDataSolicitacao(sqlSolicitacao);
                    
                    java.util.Date dataEmissao = formato.parse(req.getParameter("dataEmissao"));
                    Date sqlEmissao = new Date(dataEmissao.getTime());
                    relatorioFinal.setDataEmissao(sqlEmissao);
                    
                    usuarioSolicitacao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioSolicitacao")));
                    usuarioEmissao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioEmissao")));
                    statusRelatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioFinal")));
                    
                    relatorioFinal.setUsuarioSolicitacao(usuarioSolicitacao);
                    relatorioFinal.setUsuarioEmissao(usuarioEmissao);
                    relatorioFinal.setStatusRelatorioFinal(statusRelatorioFinal);
                    
                    //Grava um nova relatorioFinal no banco de dados
                    new RelatorioFinalDAO().Incluir(relatorioFinal);

                    //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoRelatorioFinal", relatorioFinal);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova relatorioFinal
                    relatorioFinal = new RelatorioFinal();

                    //Atribui as informações da relatorioFinal no objeto
                    relatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    relatorioFinal.setHoras(Integer.parseInt(req.getParameter("horas")));
                    
                    java.util.Date dataSolicitacao = formato.parse(req.getParameter("dataSolicitacao"));
                    Date sqlSolicitacao = new Date(dataSolicitacao.getTime());
                    relatorioFinal.setDataSolicitacao(sqlSolicitacao);
                    
                    java.util.Date dataEmissao = formato.parse(req.getParameter("dataEmissao"));
                    Date sqlEmissao = new Date(dataEmissao.getTime());
                    relatorioFinal.setDataEmissao(sqlEmissao);
                    
                    usuarioSolicitacao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioSolicitacao")));
                    usuarioEmissao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioEmissao")));
                    statusRelatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioFinal")));
                    
                    relatorioFinal.setUsuarioSolicitacao(usuarioSolicitacao);
                    relatorioFinal.setUsuarioEmissao(usuarioEmissao);
                    relatorioFinal.setStatusRelatorioFinal(statusRelatorioFinal);
                    
                    //Exclui relatorioFinal no banco de dados
                    new RelatorioFinalDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoRelatorioFinal", relatorioFinal);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova relatorioFinal
                    relatorioFinal = new RelatorioFinal();

                    //Atribui as informações da relatorioFinal no objeto
                    relatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    relatorioFinal.setHoras(Integer.parseInt(req.getParameter("horas")));
                    
                    java.util.Date dataSolicitacao = formato.parse(req.getParameter("dataSolicitacao"));
                    Date sqlSolicitacao = new Date(dataSolicitacao.getTime());
                    relatorioFinal.setDataSolicitacao(sqlSolicitacao);
                    
                    java.util.Date dataEmissao = formato.parse(req.getParameter("dataEmissao"));
                    Date sqlEmissao = new Date(dataEmissao.getTime());
                    relatorioFinal.setDataEmissao(sqlEmissao);
                    
                    usuarioSolicitacao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioSolicitacao")));
                    usuarioEmissao.setCodigo(Integer.parseInt(req.getParameter("codigoUsuarioEmissao")));
                    statusRelatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioFinal")));
                    
                    relatorioFinal.setUsuarioSolicitacao(usuarioSolicitacao);
                    relatorioFinal.setUsuarioEmissao(usuarioEmissao);
                    relatorioFinal.setStatusRelatorioFinal(statusRelatorioFinal);
                    
                    //altera relatorioFinal no banco de dados
                    new RelatorioFinalDAO().Alterar(relatorioFinal);

                    //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoRelatorioFinal", relatorioFinal);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova relatorioFinal
                    relatorioFinal = new RelatorioFinal();

                    //Grava um nova relatorioFinal no banco de dados
                    relatorioFinal = new RelatorioFinalDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaRelatorioFinal", relatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<RelatorioFinal> listaRelatorioFinal = new ArrayList<>();

                    //Grava um nova relatorioFinal no banco de dados
                    listaRelatorioFinal = new RelatorioFinalDAO().Consultar();

                    //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaRelatorioFinal", listaRelatorioFinal);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/index.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<RelatorioFinal> listaRelatorioFinal = new ArrayList<>();

            //Grava um nova relatorioFinal no banco de dados
            listaRelatorioFinal = new RelatorioFinalDAO().Consultar();

            //Atribui a ultima relatorioFinal como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaRelatorioFinal", listaRelatorioFinal);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar relatorioFinal no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/index.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
