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
public class TipoComprovante {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String descricao;
    private String intrucao;
    private String status;

    public enum ChavesDeConfiguracao {
    
	STATUS(1), INSTRUCOES(2);
	
	private final int valor;
        
	ChavesDeConfiguracao(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}  
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
     * @return the intrucao
     */
    public String getIntrucao() {
        return intrucao;
    }

    /**
     * @param intrucao the intrucao to set
     */
    public void setIntrucao(String intrucao) {
        this.intrucao = intrucao;
    }
}
