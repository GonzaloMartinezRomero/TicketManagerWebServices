/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.DAOs;

import com.ujaen.entidades.Entidad;
import com.ujaen.excepciones.ExcepcionObjetoNoExiste;
import com.ujaen.excepciones.ExcepcionObjetoRepetido;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Drebin
 */
@Repository("entidadDAO")
public class EntidadDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public EntidadDAO(){}
    
  
    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void Insertar(Entidad t) throws ExcepcionObjetoRepetido {
        try{
            this.getEm().persist(t);            
        }catch(Exception e){
            throw new ExcepcionObjetoRepetido("Error al insertar administradorDAO " + e.getMessage());
        }      
    }

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void Actualizar(Entidad t) throws ExcepcionObjetoNoExiste {
        try{
            getEm().merge(t);
        }catch(Exception e){
            throw new ExcepcionObjetoNoExiste("Error al actualizar administradorDAO " + e.getMessage());
        }   
    }

    
    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void Borrar(Entidad t) throws ExcepcionObjetoNoExiste {
        try{
            getEm().remove(em.contains(t) ? t : em.merge(t));
        }catch(Exception e){
            throw new ExcepcionObjetoNoExiste("Error al borrar administradorDAO " + e.getMessage());
        }   
    }

    
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public Entidad Buscar(Entidad t) throws ExcepcionObjetoNoExiste {
        try{
            return this.getEm().find(Entidad.class, t.getId());
        }catch(Exception e){
            throw new ExcepcionObjetoNoExiste("Error al buscar administradorDAO " + e.getMessage());
        }
    }
    
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public Entidad Buscar (String id) throws ExcepcionObjetoNoExiste{
        try {
            return this.getEm().find(Entidad.class, id);
        } catch (Exception e){
            throw new ExcepcionObjetoNoExiste ("Error al buscar administradorDAO " + e.getMessage());
        }
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
