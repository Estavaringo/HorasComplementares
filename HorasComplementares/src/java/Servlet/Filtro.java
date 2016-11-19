/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author flaviosampaioreisdelima
 */

//Todas as URI irão passar pelo filtro, com a configuração abaixo
@WebFilter(filterName = "Filtro", urlPatterns = {"/*"})
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //Descobri a URI que o usuário entrou
        String uri = req.getRequestURI();
        String usuario = getUsuario(req);

        System.out.println("Usuario " + usuario + " acessando a URI " + uri);
        chain.doFilter(request, response);
    }

    private String getUsuario(HttpServletRequest req) {

        //Pega o usuário da sessão
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            return "<deslogado>";
        }
        return usuario.getEmail();
    }

    @Override
    public void destroy() {
    }

}
