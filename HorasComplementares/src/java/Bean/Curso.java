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
public class Curso {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String nome;
    private String descricao;

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
     * @return the cargaHoraria
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
