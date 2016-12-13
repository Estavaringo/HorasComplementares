/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.DocumentoRelatorio;
import Bean.TipoComprovante;
import Bean.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author gabri
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/Upload"})
public class UploadServlet extends HttpServlet {

    //Declarações
    private Usuario usuario = null;
    private DocumentoRelatorio documentoRelatorio = null;
    private TipoComprovante tipoComprovante = null;

    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        List<FileItem> multiparts = null;
        try {
            multiparts = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(req);
        } catch (FileUploadException ex) {
            System.err.println("Erro ao recuperar o arquivo. Detalhes: " + ex.getMessage());

        }
        documentoRelatorio = new DocumentoRelatorio();
        tipoComprovante = new TipoComprovante();

        //   SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String UPLOAD_DIRECTORY = "C:/Google Drive/IFSP/5º Semestre/TCC/"
                + "Arquivos e Documentos Produzidos pela Equipe/Desenvolvimento/"
                + "NetBeans/Gabriel/HorasComplementares/HorasComplementares/web/comprovantes";

        try {
            if (ServletFileUpload.isMultipartContent(req)) {
                System.out.println(multiparts);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        String url = UPLOAD_DIRECTORY + File.separator + name;
                        item.write(new File(url));
                        System.out.println(name);
                        System.out.println(url);
                        System.out.println("Fez o Upload");
                    }
                }
                System.out.println("Não fez upload");
                req.getRequestDispatcher("/WEB-INF/Paginas/dashboard.jsp").forward(req, response);

                //File uploaded successfully
            } else {
                System.out.println("Não fez upload");
            }
        } catch (Exception ex) {
            System.err.println("Erro ao realizar o upload. Detalhes: " + ex.getMessage());
            req.getRequestDispatcher("erro.html").forward(req, response);
        }

    }
}
