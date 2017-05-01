/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

/**
 *
 * @author Drebin
 */
@Entity
public class Fila implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Integer,Asiento> mapAsientos;
    
    public Fila(){
        mapAsientos = new HashMap<>();
    }

    public Fila(Map<Integer,Asiento> _setAsientos){
        this.mapAsientos=_setAsientos;
    }
    
    
    

    /**
     * @return the mapAsientos
     */
    public Map<Integer,Asiento> getMapAsientos() {
        return mapAsientos;
    }

    /**
     * @param mapAsientos the mapAsientos to set
     */
    public void setMapAsientos(Map<Integer,Asiento> mapAsientos) {
        this.mapAsientos = mapAsientos;
    }

    
    
    
    
    
}
