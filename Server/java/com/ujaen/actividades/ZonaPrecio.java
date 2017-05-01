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
public class ZonaPrecio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    private String nombre;
    private float precio;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Integer,FilaEvento> mapaFilas;
    
    public ZonaPrecio(){
        mapaFilas = new HashMap<>();
        this.precio=0.0f;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the mapaFilas
     */
    public Map<Integer,FilaEvento> getMapaFilas() {
        return mapaFilas;
    }

    /**
     * @param mapaFilas the mapaFilas to set
     */
    public void setMapaFilas(Map<Integer,FilaEvento> mapaFilas) {
        this.mapaFilas = mapaFilas;
    }

    
    
    
}
