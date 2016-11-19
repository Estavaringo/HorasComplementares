/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author gabri
 */
public class TipoRelatorio {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    private int qtdHoras;

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
     * @return the qtdHoras
     */
    public int getQtdHoras() {
        return qtdHoras;
    }

    /**
     * @param qtdHoras the qtdHoras to set
     */
    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }
}
