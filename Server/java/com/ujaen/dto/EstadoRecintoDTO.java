/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Drebin
 */
public class EstadoRecintoDTO {
    
    private String nombreEvento;
    private int anio;
    private int mes;
    private int dia;
    
    private final List<ZonaDTO> zonas;
    
    public EstadoRecintoDTO(){
        this.zonas=new ArrayList<>();
    }
    
    public EstadoRecintoDTO(String _nombreEvento,int _anio,int _mes,int _dia,List<ZonaDTO> _zonas){
        this.zonas=_zonas;
        this.nombreEvento=_nombreEvento;
        this.anio=_anio;
        this.mes=_mes;
        this.dia=_dia;
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
     * @return the zonas
     */
    public List<ZonaDTO> getZonas() {
        return zonas;
    }
}
