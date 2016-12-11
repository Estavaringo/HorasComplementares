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
public class DocumentoRelatorio {
    
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    private String url;
    
    //Foreign Key
    private RelatorioAtividade relatorioAtividade;
    private TipoComprovante tipoDocumento;

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
     * @return the tipoDocumento
     */
    public TipoComprovante getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoComprovante tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
