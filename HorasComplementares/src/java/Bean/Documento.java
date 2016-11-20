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
public class Documento {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    private String url;
    private boolean visivel;
    private Date data;
    
    //Foreign Key
    private Usuario usuario;

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
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }

    /**
     * @return the visivel
     */
    public boolean isVisivel() {
        return visivel;
    }

    /**
     * @param visivel the visivel to set
     */
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
}
