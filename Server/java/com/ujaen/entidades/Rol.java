/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Drebin
 */
@Entity
public class Rol implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ID;
    
    private String IdUsuario;
    private String rol;
    
    public Rol(){
        this.IdUsuario=null;
        this.rol=null;
    }
    public Rol(String _IdUsuario, String _rol){
        this.IdUsuario=_IdUsuario;
        this.rol=_rol;
    }

    /**
     * @return the IdUsuario
     */
    public String getIdUsuario() {
        return IdUsuario;
    }

    /**
     * @param IdUsuario the IdUsuario to set
     */
    public void setIdUsuario(String IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    
     
    
}
