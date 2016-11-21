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
public class Historico {
    
    //Primary Key
    private int codigo;
    
    //Atributos
    private Date data;
    
    //Foreign Key
    private RelatorioAtividade relatorioAtividade;
    private StatusRelatorioAtividade statusRelatorioAtividade;

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
     * @return the statusRelatorioAtividade
     */
    public StatusRelatorioAtividade getStatusRelatorioAtividade() {
        return statusRelatorioAtividade;
    }

    /**
     * @param statusRelatorioAtividade the statusRelatorioAtividade to set
     */
    public void setStatusRelatorioAtividade(StatusRelatorioAtividade statusRelatorioAtividade) {
        this.statusRelatorioAtividade = statusRelatorioAtividade;
    }
}
