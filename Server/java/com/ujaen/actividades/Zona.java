/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;


/**
 *
 * @author Gonzalo
 */

@Entity
public class Zona implements Serializable{    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID_Zona;
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Integer,Fila> mapFilas;
    
    public Zona(){
        this.nombre = null;
        this.mapFilas=new HashMap<>();        
    }
   
    public Zona(String _nombre,Map<Integer,Fila> _setFilas){
        this.nombre=_nombre;
        this.mapFilas=_setFilas;       
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


    public boolean equals(Zona zona){
        return zona.getNombre().equals(this.getNombre());
    }

    /**
     * @return the mapFilas
     */
    public Map<Integer,Fila> getMapFilas() {
        return mapFilas;
    }

    /**
     * @param mapFilas the mapFilas to set
     */
    public void setMapFilas(Map<Integer,Fila> mapFilas) {
        this.mapFilas = mapFilas;
    }

    @JsonIgnore
    public int getCapacidadTotal(){
        int capacidad=0;
        
        for(Fila fila:this.mapFilas.values()){
            capacidad+=fila.getMapAsientos().size();
        }        
                
        return capacidad;
    }
    

    
}
