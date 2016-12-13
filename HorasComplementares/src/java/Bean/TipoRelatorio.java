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
    private String nome;
    private String descricao;
    private int qtdHoras;
    private int qtdLimiteDeEnvio;
    private String ativo;
    
    public enum ChavesDeConfiguracao {
    
	QUANTIDADE_HORAS(1), QUANTIDADE_LIMITE_DE_ENVIO(2);
	
	private final int valor;
        
	ChavesDeConfiguracao(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}  
    }
    
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

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtdLimiteDeEnvio
     */
    public int getQtdLimiteDeEnvio() {
        return qtdLimiteDeEnvio;
    }

    /**
     * @param qtdLimiteDeEnvio the qtdLimiteDeEnvio to set
     */
    public void setQtdLimiteDeEnvio(int qtdLimiteDeEnvio) {
        this.qtdLimiteDeEnvio = qtdLimiteDeEnvio;
    }

    /**
     * @return the ativo
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
