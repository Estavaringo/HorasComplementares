/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Curso;
import Bean.TipoUsuario;
import Bean.Usuario;
import DAO.CursoDAO;
import DAO.TipoUsuarioDAO;
import DAO.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
public class UsuarioServlet implements LogicaDeNegocio {

    //Declarações
    private Usuario usuario = null;
    private Curso curso = new Curso();
    private TipoUsuario tipoUsario = new TipoUsuario();
    private String tarefa;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        tarefa = req.getParameter("tarefa");
        switch (tarefa) {
            case "incluir":
                try {

                    //instancia uma nova usuario
                    usuario = new Usuario();

                    //Atribui as informações da usuario no objeto
                    if (req.getParameter("tipoUsuario").equals("aluno")) {
                        usuario.setNome(req.getParameter("nome"));
                        usuario.setProntuario(req.getParameter("prontuario"));
                        usuario.setLogin(req.getParameter("login"));
                        usuario.setSemestre(req.getParameter("semestre"));
                        usuario.setAtivo(Boolean.parseBoolean(req.getParameter("ativo")));
                        usuario.setSenha(new Criptografia().Digest(req.getParameter("senha")));

                        java.util.Date data = formato.parse(req.getParameter("data"));
                        Date sql = new Date(data.getTime());
                        usuario.setDataMatricula(sql);

                        curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                        tipoUsario.setCodigo(Integer.parseInt(req.getParameter("codigoTipoUsuario")));
                        usuario.setCurso(curso);
                        usuario.setTipoUsuario(tipoUsario);

                        //Grava um nova usuario no banco de dados
                        new UsuarioDAO().IncluirAluno(usuario);

                    } else {
                        usuario.setNome(req.getParameter("nome"));
                        usuario.setFuncional(req.getParameter("funcional"));
                        usuario.setLogin(req.getParameter("login"));
                        usuario.setAtivo(Boolean.parseBoolean(req.getParameter("ativo")));
                        usuario.setSenha(new Criptografia().Digest(req.getParameter("senha")));

                        curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                        tipoUsario.setCodigo(Integer.parseInt(req.getParameter("codigoTipoUsuario")));
                        usuario.setCurso(curso);
                        usuario.setTipoUsuario(tipoUsario);

                        //Grava um nova usuario no banco de dados
                        new UsuarioDAO().IncluirFuncionario(usuario);
                    }

                    //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("incluidoUsuario", usuario);

                } catch (SQLException | ParseException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    System.err.println("Erro ao inserir usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "remover":
                try {

                    //instancia uma nova usuario
                    usuario = new Usuario();

                    //Atribui as informações da usuario no objeto
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    usuario.setNome(req.getParameter("nome"));
                    usuario.setProntuario(req.getParameter("prontuario"));
                    usuario.setFuncional(req.getParameter("funcional"));
                    usuario.setLogin(req.getParameter("login"));
                    usuario.setSemestre(req.getParameter("semestre"));
                    usuario.setAtivo(true);

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    usuario.setDataMatricula(sql);

                    usuario.setSenha(new Criptografia().Digest(req.getParameter("senha")));

                    curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                    tipoUsario.setCodigo(Integer.parseInt(req.getParameter("codigoTipoUsuario")));

                    usuario.setCurso(curso);
                    usuario.setTipoUsuario(tipoUsario);

                    //Exclui usuario no banco de dados
                    new UsuarioDAO().Excluir(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("excluidoUsuario", usuario);

                } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException | ParseException ex) {
                    System.err.println("Erro ao remover usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "alterar":
                try {

                    //instancia uma nova usuario
                    usuario = new Usuario();

                    //Atribui as informações da usuario no objeto
                    usuario.setCodigo(Integer.parseInt(req.getParameter("codigo")));
                    usuario.setNome(req.getParameter("nome"));
                    usuario.setProntuario(req.getParameter("prontuario"));
                    usuario.setFuncional(req.getParameter("funcional"));
                    usuario.setLogin(req.getParameter("login"));
                    usuario.setSemestre(req.getParameter("semestre"));
                    usuario.setAtivo(true);

                    java.util.Date data = formato.parse(req.getParameter("data"));
                    Date sql = new Date(data.getTime());
                    usuario.setDataMatricula(sql);

                    usuario.setSenha(new Criptografia().Digest(req.getParameter("senha")));

                    curso.setCodigo(Integer.parseInt(req.getParameter("codigoCurso")));
                    tipoUsario.setCodigo(Integer.parseInt(req.getParameter("codigoTipoUsuario")));

                    usuario.setCurso(curso);
                    usuario.setTipoUsuario(tipoUsario);

                    //altera usuario no banco de dados
                    new UsuarioDAO().Alterar(usuario);

                    //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("alteradoUsuario", usuario);

                } catch (SQLException | ParseException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    System.err.println("Erro ao alterar usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;

            case "consultar":
                try {

                    //instancia uma nova usuario
                    usuario = new Usuario();

                    //Grava um nova usuario no banco de dados
                    usuario = new UsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codigo")));

                    //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("consultaUsuario", usuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao consultar usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }
                break;
            case "consultarLista":
                try {

                    ArrayList<Usuario> listaUsuario = new ArrayList<>();
                    ArrayList<Curso> listaCurso = new ArrayList<>();
                    ArrayList<TipoUsuario> listaTipoUsuario = new ArrayList<>();

                    //Grava um nova usuario no banco de dados
                    listaUsuario = new UsuarioDAO().Consultar();
                    listaCurso = new CursoDAO().Consultar();
                    listaTipoUsuario = new TipoUsuarioDAO().Consultar();

                    //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
                    req.setAttribute("listaUsuario", listaUsuario);
                    req.setAttribute("listaCurso", listaCurso);
                    req.setAttribute("listaTipoUsuario", listaTipoUsuario);

                } catch (SQLException ex) {
                    System.err.println("Erro ao cosultar usuario no banco de dados. Detalhes: " + ex.getMessage());
                    return "erro.html";
                }

                return "/WEB-INF/Paginas/usuario.jsp";

            default:
                System.err.println("Tarefa informada é inválida!");
                return "erro.html";

        }

        try {

            ArrayList<Usuario> listaUsuario = new ArrayList<>();

            //Grava um nova usuario no banco de dados
            listaUsuario = new UsuarioDAO().Consultar();

            //Atribui a ultima usuario como Atributo a ser enviado na próxima Requisição 
            req.setAttribute("listaUsuario", listaUsuario);

        } catch (SQLException ex) {
            System.err.println("Erro ao cosultar usuario no banco de dados. Detalhes: " + ex.getMessage());
            return "erro.html";
        }

        return "/WEB-INF/Paginas/usuario.jsp";
    }

    @Override
    public boolean verifica() {
        return true;
    }

}
