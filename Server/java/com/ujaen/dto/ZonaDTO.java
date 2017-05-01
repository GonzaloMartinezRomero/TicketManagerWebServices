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
public class ZonaDTO {
    
    private String nombreZona;
    private final List<FilaDTO> listaFilas;
    
    public ZonaDTO(){
        this.listaFilas=new ArrayList<>();
    }
     public ZonaDTO(String _nombreZona){
         
        this.nombreZona=_nombreZona;
        this.listaFilas=new ArrayList<>();
    }
    
    public ZonaDTO(String _nombreZona,List<FilaDTO> _listaFilas){
        this.nombreZona=_nombreZona;
        this.listaFilas=_listaFilas;
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
     * @return the listaFilas
     */
    public List<FilaDTO> getListaFilas() {
        return listaFilas;
    }
    
}
