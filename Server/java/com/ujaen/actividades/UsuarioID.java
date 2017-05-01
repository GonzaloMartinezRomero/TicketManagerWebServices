/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.*;

/**
 *
 * @author Drebin
 */

@Entity
public class UsuarioID implements Serializable{
    
    /*
        ID no es la clave del usuario, ya que ésta viene reflejada en el mapa
        Map<String,UsuarioID>. Ésta ID es solo para la BD.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Integer,Ticket> mapaTickets;
    
    public UsuarioID(){
        this.mapaTickets=new TreeMap<>();
    }    

    /**
     * @return the mapaTickets
     */
    public Map<Integer,Ticket> getMapaTickets() {
        return mapaTickets;
    }

    /**
     * @param mapaTickets the mapaTickets to set
     */
    public void setMapaTickets(Map<Integer,Ticket> mapaTickets) {
        this.mapaTickets = mapaTickets;
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
    
}
