/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.dto;

/**
 *
 * @author Drebin
 */
public class TicketDTO {
    
    private String nombreEvento;
    private int dia;
    private int mes;
    private int anio;
    private String nombreZona;
    private int fila;
    private int asiento;
    private String ID_Usuario;
    private int ID_Ticket;
    private boolean anulado;
    
    
    public TicketDTO(){}
    
    public TicketDTO(String _nombreEvento,String _nombreZona,int _fila,int _asiento,String _ID_Usuario,int _ID_Ticket, boolean _anulado){
        
        this.nombreEvento=_nombreEvento;
        this.nombreZona=_nombreZona;
        this.fila=_fila;
        this.asiento=_asiento;
        this.ID_Usuario=_ID_Usuario;
        this.ID_Ticket=_ID_Ticket;
        this.anulado = _anulado;
    }

    /**
     * @return the nombreEvento
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * @param nombreEvento the nombreEvento to set
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the nombreZona
     */
    public String getNombreZona() {
        return nombreZona;
    }

    /**
     * @param nombreZona the nombreZona to set
     */
    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the asiento
     */
    public int getAsiento() {
        return asiento;
    }

    /**
     * @param asiento the asiento to set
     */
    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    /**
     * @return the ID_Usuario
     */
    public String getID_Usuario() {
        return ID_Usuario;
    }

    /**
     * @param ID_Usuario the ID_Usuario to set
     */
    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    /**
     * @return the ID_Ticket
     */
    public int getID_Ticket() {
        return ID_Ticket;
    }

    /**
     * @param ID_Ticket the ID_Ticket to set
     */
    public void setID_Ticket(int ID_Ticket) {
        this.ID_Ticket = ID_Ticket;
    }

    /**
     * @return the anulado
     */
    public boolean isAnulado() {
        return anulado;
    }

    /**
     * @param anulado the anulado to set
     */
    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
    
    
    
    
}
