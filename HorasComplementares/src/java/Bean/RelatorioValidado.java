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
public class RelatorioValidado {
    
    //Primary Key
    private int codigo;
    
    //Atributos
    private String comentario;
    private Date dataValidacao;
    private int horasValidadas;
    
    //Foreign Key
    private RelatorioAtividade relatorioAtividade;
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
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the dataValidacao
     */
    public Date getDataValidacao() {
        return dataValidacao;
    }

    /**
     * @param dataValidacao the dataValidacao to set
     */
    public void setDataValidacao(Date dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    /**
     * @return the horasValidadas
     */
    public int getHorasValidadas() {
        return horasValidadas;
    }

    /**
     * @param horasValidadas the horasValidadas to set
     */
    public void setHorasValidadas(int horasValidadas) {
        this.horasValidadas = horasValidadas;
    }

    /**
     * @return the relatorioAtividade
     */
    public RelatorioAtividade getRelatorioAtividade() {
        return relatorioAtividade;
    }

    /**
     * @param relatorioAtividade the relatorioAtividade to set
     */
    public void setRelatorioAtividade(RelatorioAtividade relatorioAtividade) {
        this.relatorioAtividade = relatorioAtividade;
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
    
}
