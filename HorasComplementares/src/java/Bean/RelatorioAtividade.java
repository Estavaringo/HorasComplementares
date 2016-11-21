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
public class RelatorioAtividade {
    
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    private String semestre;
    private String resumo;
    private Date data;
    
    //Foreign Key
    private Usuario usuario;
    private RelatorioFinal relatorioFinal;
    private TipoRelatorio tipoRelatorio;
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
     * @return the semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the resumo
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
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
     * @return the relatorioFinal
     */
    public RelatorioFinal getRelatorioFinal() {
        return relatorioFinal;
    }

    /**
     * @param relatorioFinal the relatorioFinal to set
     */
    public void setRelatorioFinal(RelatorioFinal relatorioFinal) {
        this.relatorioFinal = relatorioFinal;
    }

    /**
     * @return the tipoRelatorio
     */
    public TipoRelatorio getTipoRelatorio() {
        return tipoRelatorio;
    }

    /**
     * @param tipoRelatorio the tipoRelatorio to set
     */
    public void setTipoRelatorio(TipoRelatorio tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
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
