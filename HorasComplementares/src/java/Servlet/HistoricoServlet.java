/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Historico;
import Bean.RelatorioAtividade;
import Bean.StatusRelatorioAtividade;
import Bean.TipoRelatorio;
import Bean.Usuario;
import DAO.HistoricoDAO;
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
public class HistoricoServlet implements LogicaDeNegocio{

    //Declarações
    private Historico historico = null;
    private StatusRelatorioAtividade statusRelatorioAtividade = new StatusRelatorioAtividade();
    private RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova historico
                    historico = new Historico();

                    //Atribui as informações da historico no objeto
                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    historico.setData(sql);

                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));
                    
                    historico.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    historico.setRelatorioAtividade(relatorioAtividade);
                    
                    //Grava um nova historico no banco de dados
                    new HistoricoDAO().Incluir(historico);

                    //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoHistorico", historico);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir historico no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova historico
                    historico = new Historico();

                    //Atribui as informações da historico no objeto
                    historico.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    
                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    historico.setData(sql);

                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));
                    
                    historico.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    historico.setRelatorioAtividade(relatorioAtividade);
                    
                    //Exclui historico no banco de dados
                    new HistoricoDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoHistorico", historico);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover historico no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova historico
                    historico = new Historico();

                    //Atribui as informações da historico no objeto
                    historico.setCodigo(Integer.parseInt(req.getParameter("codigo")));

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    historico.setData(sql);

                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));
                    
                    historico.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    historico.setRelatorioAtividade(relatorioAtividade);
                    
                    //altera historico no banco de dados
                    new HistoricoDAO().Alterar(historico);

                    //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoHistorico", historico);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar historico no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova historico
                    historico = new Historico();

                    //Grava um nova historico no banco de dados
                    historico = new HistoricoDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaHistorico", historico);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar historico no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<Historico> listaHistorico = new ArrayList<>();

                    //Grava um nova historico no banco de dados
                    listaHistorico = new HistoricoDAO().Consultar();

                    //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaHistorico", listaHistorico);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar historico no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/index.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<Historico> listaHistorico = new ArrayList<>();

            //Grava um nova historico no banco de dados
            listaHistorico = new HistoricoDAO().Consultar();

            //Atribui a ultima historico como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaHistorico", listaHistorico);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar historico no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/index.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
