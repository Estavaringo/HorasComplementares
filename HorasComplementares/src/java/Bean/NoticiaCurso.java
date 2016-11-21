/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.Date;

/**
 *
 * @author gabri
 */
public class NoticiaCurso {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    
    //Foreign Key
    private Curso curso;
    private Noticia noticia;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the noticia
     */
    public Noticia getNoticia() {
        return noticia;
    }

    /**
     * @param noticia the noticia to set
     */
    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
}
