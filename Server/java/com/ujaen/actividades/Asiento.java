/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Drebin
 */
@Entity
public class Asiento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    
    
    public Asiento(){}
    
    
}
