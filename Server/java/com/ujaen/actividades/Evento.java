/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujaen.actividades;


import com.ujaen.DAOs.EventoDAO;
import com.ujaen.excepciones.*; 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.*;

/**
 *
 * @author Gonzalo
 */
@Entity
@IdClass(EventoDAO.PrimaryKey.class)
public class Evento implements Serializable{

    public enum CategoriaEvento{
     Musica,Teatro,Festivales,Deporte
    }
    
    @Id
    private String nombre;
       
    @Id
    @Temporal(TemporalType.DATE)
    private Calendar fecha;    
    
    private CategoriaEvento tipo;

    @OneToOne   
    private Recinto recinto;
        
    //True=Vendido // False=No Vendido
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<String,ZonaPrecio> mapaZonas;
    
    //Mapa donde se guardan los tickets que han sido vendidos
    @OneToMany(cascade ={CascadeType.ALL},fetch = FetchType.EAGER)
    private Map<String,UsuarioID> mapaTicketsVendidos;    
    
    private int Codigo_Ticket;    
    
    public Evento(){
        this.nombre=null;
        this.tipo=null;
        this.recinto=null;
        this.fecha=null;        
        this.mapaZonas=new TreeMap<>();                    
        this.mapaTicketsVendidos=new TreeMap<>();        
        this.Codigo_Ticket=0;
    }
    
    public Evento(Recinto _recinto,String _nombre,CategoriaEvento _tipo,Calendar _fecha) throws ExcepcionRecinto{
       
        this.recinto=_recinto;           
        this.nombre=_nombre;
        this.tipo=_tipo;
        this.fecha=_fecha;
        this.Codigo_Ticket=0;
        this.mapaTicketsVendidos=new TreeMap<>();
        this.mapaZonas=new TreeMap<>();
         
        
        //Cargar mapa de las zonas
        try{
            this.cargarMapaZonas();
        }catch(ExcepcionRecinto e){
            throw new ExcepcionRecinto(e.getMessage());
        }
    }
    
      
    private void cargarMapaZonas() throws ExcepcionRecinto{
        
        if(this.recinto==null){
            throw new ExcepcionRecinto("No se ha asignado ningun recinto");
        }
        else{              
            int cont=0;
           
            //Guardo la zona
            for(Zona zona:recinto.getMapaZonas().values()){    

                ZonaPrecio zonaPrecio=new ZonaPrecio();

                //Guardo las filas de la zona
                for(Fila fila:zona.getMapFilas().values()){

                    //Guardo los asientos por cada fila de la zona                      
                    FilaEvento filaEvento=new FilaEvento();

                    for(int i=0;i<fila.getMapAsientos().size();i++){                        
                        filaEvento.getMapAsientos().put(i, new AsientoEvento());
                    }
                    zonaPrecio.getMapaFilas().put(cont++, filaEvento);
                    zonaPrecio.setNombre(zona.getNombre());
                }      
                cont=0;
                this.mapaZonas.put(zona.getNombre(), zonaPrecio);
             }        
           }
        }       
        
    public void anularTicket(int ID_Ticket,String ID_Usuario) throws ExcepcionTicket{        
            
        if(this.mapaTicketsVendidos.containsKey(ID_Usuario)){
            
            Ticket ticketAux=this.mapaTicketsVendidos.get(ID_Usuario).getMapaTickets().get(ID_Ticket);
             
            if(ticketAux!=null){

                if(!this.mapaZonas.get(ticketAux.getNombreZona()).getMapaFilas().get(ticketAux.getFila()).getMapAsientos().get(ticketAux.getAsiento()).isEstado()){
                     throw new ExcepcionTicket("Zona a la que hace referencia ya esta libre"); 
                }else{

                    if(!ticketAux.isAnulado()){

                         //Asiento vuelve a quedar libre
                         this.mapaZonas.get(ticketAux.getNombreZona()).getMapaFilas().get(ticketAux.getFila()).getMapAsientos().get(ticketAux.getAsiento()).setEstado(false);

                         //Ticket emitido queda anulado
                         ticketAux.setAnulado(true);

                         //Finalmente se sobreescribe NO SE BORRA!!
                         this.mapaTicketsVendidos.get(ID_Usuario).getMapaTickets().put(ID_Ticket, ticketAux);

                    }else{
                        throw new ExcepcionTicket("El ticket esta anulado");
                    }                    
                }

            }else{
                throw new ExcepcionTicket("Error ID ticket no ha sido emitido"); 
            }
            
            
        }else{
            throw new ExcepcionTicket("Usuario no tiene tickets comprados");
        }
        
       
           
    }
    
    public Ticket comprarTicket(String zona,int fila,int asiento,String ID_Usuario) throws ExcepcionTicket,ExcepcionZona{
       
        try{   
            
            if(!this.mapaZonas.get(zona).getMapaFilas().get(fila).getMapAsientos().get(asiento).isEstado()){                    

                //Genero un ticket
                Ticket ticket=new Ticket(this.Codigo_Ticket++,zona,this.mapaZonas.get(zona).getPrecio(),this.getNombre(),this.getFecha(),Calendar.getInstance(),fila,asiento);

                //Guardo en mi estructura
                if( this.mapaTicketsVendidos.containsKey(ID_Usuario)){
                    
                    this.mapaTicketsVendidos.get(ID_Usuario).getMapaTickets().put(ticket.getCodigo(), ticket);
                    
                }else{
                    
                    UsuarioID usuarioID=new UsuarioID();
                    usuarioID.getMapaTickets().put(ticket.getCodigo(), ticket);
                    
                    this.mapaTicketsVendidos.put(ID_Usuario, usuarioID);
                }
                

                //Asigno la zona como vendida
                this.mapaZonas.get(zona).getMapaFilas().get(fila).getMapAsientos().get(asiento).setEstado(true);

                return ticket;

            }else{
                throw new ExcepcionTicket("Zona ya esta vendida");
            }   
            
         }catch(NullPointerException e){
             throw new ExcepcionZona("Zona indicada no existe");
         }
          
    }
        
    public void setPrecioZona(String zona,float precio)throws ExcepcionZona{
         
        if(this.mapaZonas.containsKey(zona)){
            this.mapaZonas.get(zona).setPrecio(precio);
        }else{
            throw new ExcepcionZona("Zona indicada no existe");
        }            
    }
    
    public float getPrecioZona(String zona) throws ExcepcionZona{
         
        if(this.mapaZonas.containsKey(zona)){
            return this.mapaZonas.get(zona).getPrecio();            
        }else{
            throw new ExcepcionZona("Zona indicada no existe");
        }
        
    }
       
    public Map<Integer,Ticket> getTicketsUsuario(String ID_Usuario) throws ExcepcionObjetoNoExiste{
        
        if(this.mapaTicketsVendidos.containsKey(ID_Usuario)){
            
            return this.mapaTicketsVendidos.get(ID_Usuario).getMapaTickets();
            
        }else{
            throw new ExcepcionObjetoNoExiste("Usuario no tiene tickets comprados");
        }
    }
    
    
    
     /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo
     */
    public CategoriaEvento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(CategoriaEvento tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fecha
     */
    public Calendar getFecha() {
        return this.fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
    /**
     * @return the recinto
     */
    public Recinto getRecinto() {
        return recinto;
    }
    
     /**
     * @param recinto the recinto to set
     */
    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }
    
    /**
     * @param nombreZona
     * @return the mapaZonas
     * @throws com.ujaen.excepciones.ExcepcionRecinto
     */
    public List <List<Boolean>> getMapaEstadoZonas(String nombreZona) throws  ExcepcionRecinto {
        
        List <List<Boolean>> mapaEstadoZonas = new ArrayList();
        
        if (this.recinto == null) {
            throw new ExcepcionRecinto("No se ha asignado ningun recinto");
        } else {
            //Guardo la zona¡
                ZonaPrecio zonaSel = this.mapaZonas.get(nombreZona);
                
                //Guardo las filas de la zona
                for (FilaEvento fila : zonaSel.getMapaFilas().values()) {

                    List <Boolean> listAux = new ArrayList();                    

                    for (AsientoEvento asiento : fila.getMapAsientos().values()) {
                        listAux.add(asiento.isEstado());
                    }
                    mapaEstadoZonas.add(listAux);
                }

            }
        
        return mapaEstadoZonas;
    }

}
