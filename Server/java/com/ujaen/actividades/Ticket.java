/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;


/**
 *
 * @author Gonzalo
 */
@Entity
public class Ticket implements Serializable{
  
    //--ID PARA EL EVENTO
    private int codigo;
    //--ID PARA LA BD
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;       
    
    private String nombreEvento;    
    @Temporal(TemporalType.DATE)
    private Calendar fechaEvento;
    private String nombreZona;
    private float precio;
    @Temporal(TemporalType.DATE)
    private Calendar fechaVenta;
    private int fila;
    private int asiento;
    private boolean anulado;
    
    public Ticket(){
        this.codigo=0;
        this.nombreZona=null;
        this.precio=0.0f;
        this.nombreEvento=null;
        this.fechaEvento=null;
        this.fechaVenta=null;
        this.fila=-1;
        this.asiento=-1;
        this.anulado=false;
    }
    public Ticket(int _codigo,String _nombreZona,float _precio,String _nombreEvento,Calendar _fechaEvento,Calendar _fechaVenta,int _fila,int _asiento){         
        this.codigo=_codigo;
        this.nombreZona=_nombreZona;
        this.precio=_precio;
        this.nombreEvento=_nombreEvento;
        this.fechaVenta=_fechaVenta;
        this.fila=_fila;
        this.asiento=_asiento;
        this.anulado=false;
        this.fechaEvento=_fechaEvento;
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
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
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
     * @return the fechaVenta
     */
    public Calendar getFechaVenta() {
        return fechaVenta;
    }

    /**
     * @param fechaVenta the fechaVenta to set
     */
    public void setFechaVenta(Calendar fechaVenta) {
        this.fechaVenta = fechaVenta;
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

    /**
     * @return the fechaEvento
     */
    public Calendar getFechaEvento() {
        return fechaEvento;
    }

    /**
     * @param fechaEvento the fechaEvento to set
     */
    public void setFechaEvento(Calendar fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    
    
    
}
