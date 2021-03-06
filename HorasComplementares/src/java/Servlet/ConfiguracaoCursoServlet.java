/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Curso;
import Bean.RelatorioAtividade;
import Bean.RelatorioFinal;
import Bean.StatusRelatorioAtividade;
import Bean.TipoComprovante;
import Bean.TipoRelatorio;
import Bean.Usuario;
import DAO.CursoDAO;
import DAO.RelatorioAtividadeDAO;
import DAO.TipoRelatorioDAO;
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
public class ConfiguracaoCursoServlet implements LogicaDeNegocio {

    //Declarações
    private Curso curso = null;
    private TipoRelatorio tipoRelatorio = null;
    private TipoComprovante tipoComprovante = null;
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir_tipoRelatorio":
                try {
                    
                    //Instancia os objetos a serem utilizados
                    tipoRelatorio = new TipoRelatorio();
                    curso = new Curso();                    
                    
                    //Obtem o código do curso
                    curso.setCodigo();
                    
                    //Atribui as informações da relatorioAtividade no objeto
                    tipoRelatorio.set (req.getParameter("descricao"));
                    tipoRelatorio.setResumo(req.getParameter("resumo"));
                    tipoRelatorio.setSemestre(req.getParameter("semestre"));
                    
                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioAtividade.setData(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigoTipoRelatorio")));
                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioFinal")));
                    
                    relatorioAtividade.setUsuario(usuario);
                    relatorioAtividade.setTipoRelatorio(tipoRelatorio);
                    relatorioAtividade.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    relatorioAtividade.setRelatorioFinal(relatorioFinal);
                    
                    //Grava um nova relatorioAtividade no banco de dados
                    new RelatorioAtividadeDAO().Incluir(relatorioAtividade);

                    //Atribui a ultima relatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoRelatorioAtividade", relatorioAtividade);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao inserir relatorioAtividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova relatorioAtividade
                    relatorioAtividade = new RelatorioAtividade();

                    //Atribui as informações da relatorioAtividade no objeto
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    relatorioAtividade.setDescricao(req.getParameter("descricao"));
                    relatorioAtividade.setResumo(req.getParameter("resumo"));
                    relatorioAtividade.setSemestre(req.getParameter("semestre"));
                    
                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioAtividade.setData(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigoTipoRelatorio")));
                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioFinal")));
                    
                    relatorioAtividade.setUsuario(usuario);
                    relatorioAtividade.setTipoRelatorio(tipoRelatorio);
                    relatorioAtividade.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    relatorioAtividade.setRelatorioFinal(relatorioFinal);
                    
                    //Exclui relatorioAtividade no banco de dados
                    new RelatorioAtividadeDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima relatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoRelatorioAtividade", relatorioAtividade);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao remover relatorioAtividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova relatorioAtividade
                    relatorioAtividade = new RelatorioAtividade();

                    //Atribui as informações da relatorioAtividade no objeto
                    relatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    relatorioAtividade.setDescricao(req.getParameter("descricao"));
                    relatorioAtividade.setResumo(req.getParameter("resumo"));
                    relatorioAtividade.setSemestre(req.getParameter("semestre"));
                    
                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    relatorioAtividade.setData(sql);

                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigoUsuario")));
                    tipoRelatorio.setCodigo(Integer.parseInt(req.getParameter("codigoTipoRelatorio")));
                    statusRelatorioAtividade.setCodigo(Integer.parseInt(req.getParameter("codigoStatusRelatorioAtividade")));
                    relatorioFinal.setCodigo(Integer.parseInt(req.getParameter("codigoRelatorioFinal")));
                    
                    relatorioAtividade.setUsuario(usuario);
                    relatorioAtividade.setTipoRelatorio(tipoRelatorio);
                    relatorioAtividade.setStatusRelatorioAtividade(statusRelatorioAtividade);
                    relatorioAtividade.setRelatorioFinal(relatorioFinal);
                    
                    //altera relatorioAtividade no banco de dados
                    new RelatorioAtividadeDAO().Alterar(relatorioAtividade);

                    //Atribui a ultima relatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoRelatorioAtividade", relatorioAtividade);

                } catch (SQLException | ParseException ex) {
                    System.err.println("Erro ao alterar relatorioAtividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova curso
                    curso = new Curso();

                    //Grava um nova curso no banco de dados
                    curso = new CursoDAO().Consultar(Integer.parseInt(req.getParameter("codigoCurso")));

                    //Atribui o ultima curso como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("curso", curso);
                    
                    //Consulta a lista de Relatórios
                    ArrayList<TipoRelatorio> listaTipoRelatorio = new ArrayList<>();

                    //Grava um nova tipoRelatorio no banco de dados
                    listaTipoRelatorio = new TipoRelatorioDAO().Consultar();

                    //Atribui a ultima tipoRelatorio como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaTipoRelatorio", listaTipoRelatorio);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar curso no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<RelatorioAtividade> listaRelatorioAtividade = new ArrayList<>();

                    //Grava um nova relatorioAtividade no banco de dados
                    listaRelatorioAtividade = new RelatorioAtividadeDAO().Consultar();

                    //Atribui a ultima relatorioAtividade como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaRelatorioAtividade", listaRelatorioAtividade);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar relatorioAtividade no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/index.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        return "/WEB-INF/Paginas/configuraCurso.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }
}
