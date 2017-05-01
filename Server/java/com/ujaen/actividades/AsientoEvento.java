/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Drebin
 */
@Entity
public class AsientoEvento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    private boolean estado; 
            
    public AsientoEvento(){
        this.estado=false;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
