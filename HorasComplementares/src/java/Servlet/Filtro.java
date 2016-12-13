/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author flaviosampaioreisdelima
 */
//Todas as URI irão passar pelo filtro, com a configuração abaixo
@MultipartConfig
@WebFilter(filterName = "Filtro", urlPatterns = {"/*"})
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //Descobre a URI que o usuário entrou
        String uri = req.getRequestURI();
        String usuario = getUsuario(req);
        if (ServletFileUpload.isMultipartContent(req)) {
            HttpServletRequest parsedRequest = upload(req);
            chain.doFilter(parsedRequest, response);
        } else {
            chain.doFilter(request, response);
        }
        System.out.println("Usuario " + usuario + " acessando a URI " + uri);
    }

    private String getUsuario(HttpServletRequest req) {
        //Pega o usuário da sessão
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (usuario == null) {
            return "<deslogado>";
        }
        return usuario.getNome();
    }

    @Override
    public void destroy() {
    }

    private HttpServletRequest upload(HttpServletRequest req) {
        List<FileItem> multiparts = null;
        try {
            multiparts = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(req);
        } catch (FileUploadException ex) {
            System.err.println("Erro ao recuperar o arquivo. Detalhes: " + ex.getMessage());
        }

        String UPLOAD_DIRECTORY = "C:/Google Drive/IFSP/5º Semestre/TCC/"
                + "Arquivos e Documentos Produzidos pela Equipe/Desenvolvimento/"
                + "NetBeans/Gabriel/HorasComplementares/HorasComplementares/web/comprovantes";

        try {
            System.out.println(multiparts);
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    String url = UPLOAD_DIRECTORY + File.separator + name;
                    item.write(new File(url));
                    System.out.println(name);
                    System.out.println(url);
                    System.out.println("Fez o Upload");
                    req.setAttribute("url", url);
                } else if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    req.setAttribute(fieldname, fieldvalue);
                }
            }
            return req;
        } catch (Exception ex) {
            System.err.println("Erro ao realizar o upload. Detalhes: " + ex.getMessage());
        }
        return null;
    }
}
