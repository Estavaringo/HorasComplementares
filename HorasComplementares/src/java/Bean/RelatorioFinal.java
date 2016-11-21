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
public class RelatorioFinal {
    //Primary Key
    private int codigo;
    
    //Atributos
    private int horas;
    private Date dataEmissao;
    private Date dataSolicitacao;
    
    //Foreign Key
    private Usuario usuarioEmissao;
    private Usuario usuarioSolicitacao;
    private StatusRelatorioFinal statusRelatorioFinal;

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
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the dataSolicitacao
     */
    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao the dataSolicitacao to set
     */
    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * @return the usuarioEmissao
     */
    public Usuario getUsuarioEmissao() {
        return usuarioEmissao;
    }

    /**
     * @param usuarioEmissao the usuarioEmissao to set
     */
    public void setUsuarioEmissao(Usuario usuarioEmissao) {
        this.usuarioEmissao = usuarioEmissao;
    }

    /**
     * @return the usuarioSolicitacao
     */
    public Usuario getUsuarioSolicitacao() {
        return usuarioSolicitacao;
    }

    /**
     * @param usuarioSolicitacao the usuarioSolicitacao to set
     */
    public void setUsuarioSolicitacao(Usuario usuarioSolicitacao) {
        this.usuarioSolicitacao = usuarioSolicitacao;
    }

    /**
     * @return the statusRelatorioFinal
     */
    public StatusRelatorioFinal getStatusRelatorioFinal() {
        return statusRelatorioFinal;
    }

    /**
     * @param statusRelatorioFinal the statusRelatorioFinal to set
     */
    public void setStatusRelatorioFinal(StatusRelatorioFinal statusRelatorioFinal) {
        this.statusRelatorioFinal = statusRelatorioFinal;
    }
}
