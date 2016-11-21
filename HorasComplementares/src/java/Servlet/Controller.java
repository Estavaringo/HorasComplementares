/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flaviosampaioreisdelima
 */
@WebServlet(urlPatterns = "/Executa")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classeDeNegocio = request.getParameter("logicaDeNegocio");
        System.out.println("Nome da Classe: " + classeDeNegocio);
        if(classeDeNegocio==null){
            System.out.println("OK");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("curso.jsp");
            requestDispatcher.forward(request, response);
        }
        
        //Pega o nome da classe
        String nomeDaClasse = "Servlet." + classeDeNegocio;

        try {
            //Localiza a classe através do seu nome
            Class<?> classe = Class.forName(nomeDaClasse);

            //Instancia a classe que foi localizada
            LogicaDeNegocio logicaDeNegocio = (LogicaDeNegocio) classe.newInstance();

           //Verifica se o usuario tem acesso a pagina
            if (!VerificaPermissao.executa(request, response, logicaDeNegocio)) {
               request.getRequestDispatcher("negado.jsp").forward(request, response);

            } else {
                // Recebe o nome da página que deverá ser renderiza como resposta a solicitação
                String pagina = logicaDeNegocio.executa(request, response);

                // Dispacha o usuário para página JSP
                request.getRequestDispatcher(pagina).forward(request, response);
            }

        } catch (ClassNotFoundException ex) {
            throw new ServletException("A classe informada na requisição não foi localizada.");
        } catch (InstantiationException ex) {
            throw new ServletException("Não foi possível instanciar a classse informada.");
        } catch (IllegalAccessException ex) {
            throw new ServletException("Não foi possível acessar a classe informada.");
        } catch (ServletException ex) {
            throw new ServletException(ex);
        }
    }
}
