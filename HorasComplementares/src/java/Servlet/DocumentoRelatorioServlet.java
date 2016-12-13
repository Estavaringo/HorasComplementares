/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.DocumentoRelatorio;
import Bean.RelatorioAtividade;
import Bean.TipoComprovante;
import DAO.DocumentoRelatorioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
@MultipartConfig
public class DocumentoRelatorioServlet implements LogicaDeNegocio {

    //Declarações
    private DocumentoRelatorio documentoRelatorio = null;
    private TipoComprovante tipoComprovante = new TipoComprovante();
    private RelatorioAtividade relatorioAtividade = new RelatorioAtividade();
    private String tarefa;

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {
        tarefa = (String) req.getAttribute("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova documentoRelatorio
                    documentoRelatorio = new DocumentoRelatorio();

                    //Atribui as informações da documentoRelatorio no objeto
                    documentoRelatorio.setDescricao((String) req.getAttribute("descricao"));
                    documentoRelatorio.setUrl((String) req.getAttribute("url"));

                    tipoComprovante.setCodigo(Integer.parseInt((String) req.getAttribute("codigoTipoDocumento")));
                    relatorioAtividade.setCodigo(Integer.parseInt((String) req.getAttribute("codigoRelatorioAtividade")));

                    documentoRelatorio.setTipoDocumento(tipoComprovante);
                    documentoRelatorio.setRelatorioAtividade(relatorioAtividade);

                    //Grava um nova documentoRelatorio no banco de dados
                    new DocumentoRelatorioDAO().Incluir(documentoRelatorio);

                    //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoDocumentoRelatorio", documentoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao inserir documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova documentoRelatorio
                    documentoRelatorio = new DocumentoRelatorio();

                    //Atribui as informações da documentoRelatorio no objeto
                    documentoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    documentoRelatorio.setDescricao(req.getParameter("descricao"));
                    documentoRelatorio.setUrl(req.getParameter("url"));

                    tipoComprovante.setCodigo(Integer.parseInt(req.getParameter("codigoTipoDocumento")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));

                    documentoRelatorio.setTipoDocumento(tipoComprovante);
                    documentoRelatorio.setRelatorioAtividade(relatorioAtividade);

                    //Exclui documentoRelatorio no banco de dados
                    new DocumentoRelatorioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoDocumentoRelatorio", documentoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao remover documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova documentoRelatorio
                    documentoRelatorio = new DocumentoRelatorio();

                    //Atribui as informações da documentoRelatorio no objeto
                    documentoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    documentoRelatorio.setDescricao(req.getParameter("descricao"));
                    documentoRelatorio.setUrl(req.getParameter("url"));

                    tipoComprovante.setCodigo(Integer.parseInt(req.getParameter("codigoTipoDocumento")));
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioAtividade")));

                    documentoRelatorio.setTipoDocumento(tipoComprovante);
                    documentoRelatorio.setRelatorioAtividade(relatorioAtividade);

                    //altera documentoRelatorio no banco de dados
                    new DocumentoRelatorioDAO().Alterar(documentoRelatorio);

                    //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoDocumentoRelatorio", documentoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao alterar documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova documentoRelatorio
                    documentoRelatorio = new DocumentoRelatorio();

                    //Grava um nova documentoRelatorio no banco de dados
                    documentoRelatorio = new DocumentoRelatorioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaDocumentoRelatorio", documentoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<DocumentoRelatorio> listaDocumentoRelatorio = new ArrayList<>();

                    //Grava um nova documentoRelatorio no banco de dados
                    listaDocumentoRelatorio = new DocumentoRelatorioDAO().Consultar();

                    //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaDocumentoRelatorio", listaDocumentoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/dashboard.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<DocumentoRelatorio> listaDocumentoRelatorio = new ArrayList<>();

            //Grava um nova documentoRelatorio no banco de dados
            listaDocumentoRelatorio = new DocumentoRelatorioDAO().Consultar();

            //Atribui a ultima documentoRelatorio como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaDocumentoRelatorio", listaDocumentoRelatorio);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar documentoRelatorio no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/dashboard.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
