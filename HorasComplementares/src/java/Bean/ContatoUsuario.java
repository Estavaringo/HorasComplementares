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
public class ContatoUsuario {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    
    //Foreign Key
    private Usuario usuario;
    private TipoContato tipoContato;

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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the tipoContato
     */
    public TipoContato getTipoContato() {
        return tipoContato;
    }

    /**
     * @param tipoContato the tipoContato to set
     */
    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }
}
