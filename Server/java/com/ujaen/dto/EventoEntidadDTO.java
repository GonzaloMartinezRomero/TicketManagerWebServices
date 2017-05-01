/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.dto;

/**
 *
 * @author USUARIO
 */
public class EventoEntidadDTO {
    private EntidadDTO entidad;
    private EventoDTO evento;
    
    public EventoEntidadDTO(){
        
    }
    public EventoEntidadDTO(EntidadDTO _entidad, EventoDTO _evento){
        this.entidad = _entidad;
        this.evento = _evento;
    }


    public EntidadDTO getEntidad() {
        return entidad;
    }
    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }


    public EventoDTO getEvento() {
        return evento;
    }
    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }
}
