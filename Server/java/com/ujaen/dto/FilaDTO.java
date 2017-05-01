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
public class FilaDTO {
    
    private final List<Boolean> listaAsientos;
    
    public FilaDTO(){
        this.listaAsientos=new ArrayList<>();
    
    }
    public FilaDTO(List<Boolean> _listaAsientos){
        this.listaAsientos=_listaAsientos;
    
    }

    /**
     * @return the listaAsientos
     */
    public List<Boolean> getListaAsientos() {
        return listaAsientos;
    }
    
}
