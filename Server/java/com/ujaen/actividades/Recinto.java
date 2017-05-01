/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;



import com.ujaen.DAOs.RecintoDAO;
import com.ujaen.excepciones.ExcepcionZona;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;


/**
 *
 * @author Gonzalo
 */
@Entity
@IdClass(RecintoDAO.PrimaryKey.class)
public class Recinto implements Serializable{
 
    @Id
    private String localidad;
    @Id
    private String direccion;    
    
   
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<String,Zona> mapaZonas;
    
    public Recinto(){        
        this.mapaZonas=new HashMap<>();
        this.localidad=null;
        this.direccion=null;  
    }
    public Recinto(String _localidad,String _direccion,Map<String,Zona> _mapaZonas){
        this.mapaZonas=new HashMap<>();
        this.localidad=_localidad;
        this.direccion=_direccion;
        this.mapaZonas=_mapaZonas;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the mapaZonas
     */
    public Map<String,Zona> getMapaZonas() {
        return mapaZonas;
    }

    /**
     * @param mapaZonas the mapaZonas to set
     */
    public void setMapaZonas(Map<String,Zona> mapaZonas) {
        this.mapaZonas = mapaZonas;
    }
       
 
    /**
     *
     * @param zona
     * @throws com.ujaen.excepciones.ExcepcionZona
     */    
    public void setNuevaZona(Zona zona) throws ExcepcionZona{     
        
        if(this.getMapaZonas().containsKey(zona.getNombre()))
            throw new ExcepcionZona("Zona ya existe");
        else
            this.getMapaZonas().put(zona.getNombre(), zona);
    }
    
    /**
     *
     * @param nombre
     * @return Zona     
     * Return null si no la encuentra
     */
    public Zona getZona(String nombre){
        return this.getMapaZonas().get(nombre);  
    }
    
    public void eliminarZona(String nombre) throws ExcepcionZona{
        if(this.getMapaZonas().remove(nombre)==null)
            throw new ExcepcionZona("Zona no ha sido encontrada");      
    }       
    
    public boolean existeZona(String nombreZona){
        return this.getMapaZonas().containsKey(nombreZona);
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){        
         if(obj instanceof Recinto){
            if(this.getDireccion().equals(((Recinto)obj).getDireccion()))
                if(this.getLocalidad().equals(((Recinto)obj).getLocalidad()))
                    return true;
         }        
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.localidad.hashCode()+this.direccion.hashCode();
    }

   

    
    
}
