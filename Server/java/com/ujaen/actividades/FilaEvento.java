/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author Drebin
 */
@Entity
public class FilaEvento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Integer,AsientoEvento> mapAsientos;
    
    public FilaEvento(){
        mapAsientos = new HashMap();
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * @return the mapAsientos
     */
    public Map<Integer,AsientoEvento> getMapAsientos() {
        return mapAsientos;
    }

    /**
     * @param mapAsientos the mapAsientos to set
     */
    public void setMapAsientos(Map<Integer,AsientoEvento> mapAsientos) {
        this.mapAsientos = mapAsientos;
    }
    
}
