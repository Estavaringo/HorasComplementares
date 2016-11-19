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
public class Usuario {
    //Primary Key
    private int codigo;
    
    //Atributos
    private String nome;
    private String prontuario;
    private String funcional;
    private Date dataMatricula;
    private String semestre;
    private String login;
    private String senha;
    private boolean ativo;
    
    //Foreign Key
    private TipoUsuario tipoUsuario;
    private Curso curso;
    
}
