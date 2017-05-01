/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.DAOs;
import com.ujaen.actividades.Recinto;
import com.ujaen.excepciones.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Drebin
 */

@Repository("recintoDAO")
public class RecintoDAO {
    
        
    @PersistenceContext
    private EntityManager em;

    public RecintoDAO(){ }

       
    //Clase necesaria para buscar objetos en la BD con clave compleja
    public static class PrimaryKey implements Serializable{

       private String localidad;
       private String direccion;

       public PrimaryKey(){}
       

       @Override
       public boolean equals(Object obj){
           if(obj instanceof PrimaryKey){

               PrimaryKey pk=(PrimaryKey) obj;

               if(pk.getDireccion().equals(direccion)){
                   if(pk.getLocalidad().equals(localidad))
                       return true;
               }                
           }
           return false;
       }

       @Override
       public int hashCode(){
           return this.localidad.hashCode()+this.direccion.hashCode();
       }

       public String getLocalidad(){
           return this.localidad;
       }
       public void setLocalidad(String _localidad){
           this.localidad=_localidad;
       }
       public String getDireccion(){
           return this.direccion;
       }
       public void setDireccion(String _direccion){
           this.direccion=_direccion;
       }        
   }    
       
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class,readOnly = false)
    public void Insertar(Recinto t) throws ExcepcionObjetoRepetido{      
       try{
           this.em.persist(t);
       }catch(Exception e){           
            throw new ExcepcionObjetoRepetido("recintoDAO Insertar:msg: "+ e.getMessage());
       }      
    }

    
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =Throwable.class,readOnly = false)
    public void Actualizar(Recinto t) throws ExcepcionObjetoNoExiste{
        
        try{
            this.em.merge(t);
        }catch(Exception e){
           throw new ExcepcionObjetoNoExiste("recintoDAO Actualizar:msg: "+ e.getMessage());
        }

    }

       
    
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class,readOnly = false)
    public void Borrar(String localidad,String direccion) throws ExcepcionObjetoNoExiste {

        PrimaryKey pk = new PrimaryKey();
        pk.setLocalidad(localidad);
        pk.setDireccion(direccion);
        try{
            this.em.remove(this.em.find(Recinto.class, pk));
        }catch(Exception e){
           throw new ExcepcionObjetoNoExiste("recintoDAO Borrar:msg: "+ e.getMessage());
        }    
    }

    
   
       
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Throwable.class, readOnly = true)
    public Recinto Buscar(String localidad,String direccion) {
       
        PrimaryKey pk=new PrimaryKey();
        pk.setLocalidad(localidad);
        pk.setDireccion(direccion);      
        
        return this.em.find(Recinto.class, pk);
            
    }
           
    public List<Recinto> getTodosRecintos() {        
        TypedQuery<Recinto> queryRecintos=this.em.createQuery("SELECT r FROM Recinto r",Recinto.class);
        return queryRecintos.getResultList();
    }
    
    public List<Recinto> getListaRecintos (String localidad){
        List < Recinto > listaRecintos = this.em.createQuery("SELECT r FROM Recinto r where r.localidad like ?1")
                .setParameter(1, localidad)
                .getResultList();
        
        return listaRecintos;
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


