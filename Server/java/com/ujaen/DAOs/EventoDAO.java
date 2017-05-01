/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.DAOs;

import com.ujaen.actividades.Evento;
import com.ujaen.excepciones.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Drebin
 */
@Repository("eventoDAO")
public class EventoDAO {

    @PersistenceContext
    private EntityManager em;

    public EventoDAO(){}
    
    
    public static class PrimaryKey implements Serializable{
    
        private String nombre;
        private Calendar fecha;
       

        public PrimaryKey(){}
       

       @Override
       public boolean equals(Object obj){
           if(obj instanceof PrimaryKey){
               PrimaryKey pk=(PrimaryKey) obj;               
               if(pk.getNombre().equals(this.nombre))
                   if(pk.fecha.equals(this.fecha))
                        return true;                          
           }
           return false;
       }

       @Override
       public int hashCode(){
           return this.nombre.hashCode()+this.fecha.hashCode();
       }

     
        /**
         * @param nombre the nombre to set
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * @param fecha the fecha to set
         */
        public void setFecha(Calendar fecha) {
            this.fecha = fecha;
        }


        /**
         * @return the nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * @return the fecha
         */
        public Calendar getFecha() {
            return fecha;
        }
       
    }
    
    
    public List<Evento> getTodosEventos() {
        Query query=getEm().createQuery("SELECT e FROM Evento e");
        return query.getResultList();        
    }

    
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class, readOnly = false)
    public void Insertar(Evento t)throws ExcepcionObjetoRepetido {    
       try{
           this.em.persist(t);  
       }catch(Exception e){
           throw new ExcepcionObjetoRepetido("EventoDAO Insertar:msg: "+e.getMessage());
       }      
    }
    
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class, readOnly = false)
    public void Actualizar(Evento t) throws ExcepcionObjetoNoExiste {
        try{
            this.em.merge(t);
        }catch(Exception e){
            throw new ExcepcionObjetoNoExiste("EventoDAO Actualizar:msg: "+e.getMessage());
        }    
    }
    
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class, readOnly = false)
    public void Borrar(Evento t) throws ExcepcionObjetoNoExiste{
        try{
            this.em.remove(em.contains(t) ? t : em.merge(t));
        }catch(Exception e){
            throw new ExcepcionObjetoNoExiste("EventoDAO Borrar:msg: "+e.getMessage());
        }       
    }
        
   @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Throwable.class, readOnly = true)
    public Evento Buscar(Calendar fecha,String nombre){
        
        PrimaryKey pk=new PrimaryKey();
        pk.setFecha(fecha);
        pk.setNombre(nombre);
        
        return this.em.find(Evento.class, pk);          
    }   
    
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Throwable.class, readOnly = true)
    public List<Evento>  Buscar(String nombre){
        
        List<Evento> listaEventos=getEm().createQuery("SELECT e FROM Evento e WHERE e.nombre like ?1")
                                         .setParameter(1, nombre)
                                          .getResultList();
        return listaEventos;     
    }   
    
    
    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
