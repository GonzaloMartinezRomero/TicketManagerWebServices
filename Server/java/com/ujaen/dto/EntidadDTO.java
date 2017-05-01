/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.dto;

/**
 *
 * @author Drebin
 */
public class EntidadDTO {
    
    
    private String identificador;
    private String password;
    
    public EntidadDTO(){}
    
    public EntidadDTO(String _identificador,String _password){
        this.identificador=_identificador;
        this.password=_password;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
