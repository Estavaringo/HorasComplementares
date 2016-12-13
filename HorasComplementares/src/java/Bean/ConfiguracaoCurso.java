/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Flávio Sampaio Reis de Lima
 */
public class ConfiguracaoCurso {
    
    //Primary Key
    private int codigo;
        
    //Atributos
    private TipoComprovante tipoComprovante;
    private TipoRelatorio tipoRelatorio;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoComprovante getTipoComprovante() {
        return tipoComprovante;
    }

    public void setTipoComprovante(TipoComprovante tipoComprovante) {
        this.tipoComprovante = tipoComprovante;
    }

    public TipoRelatorio getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(TipoRelatorio tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }
    
    public enum TipoConfiguracao {
    
	TIPO_RELATORIO(1), TIPO_COMPROVANTE(2);
	
	private final int valor;
        
	TipoConfiguracao(int valorOpcao){
		valor = valorOpcao;
	}
	public int getValor(){
		return valor;
	}  
    }
}
