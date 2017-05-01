package com.ujaen.servidor;

import com.ujaen.DAOs.EntidadDAO;
import com.ujaen.DAOs.EventoDAO;
import com.ujaen.DAOs.RecintoDAO;
import com.ujaen.actividades.Evento;
import com.ujaen.actividades.Recinto;

import com.ujaen.actividades.Ticket;
import com.ujaen.actividades.Zona;
import com.ujaen.dto.EventoEntidadDTO;
import com.ujaen.dto.EntidadDTO;
import com.ujaen.dto.EstadoRecintoDTO;
import com.ujaen.dto.EventoDTO;
import com.ujaen.dto.FilaDTO;
import com.ujaen.dto.RecintoDTO;
import com.ujaen.dto.TicketDTO;
import com.ujaen.dto.ZonaDTO;
import com.ujaen.entidades.Entidad;
import com.ujaen.excepciones.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Drebin
 */
@RestController
public class GestorCompraTickets{
    
    @Autowired
    @Qualifier("recintoDAO")
    private RecintoDAO recintoDAO;
    
    @Autowired
    @Qualifier("eventoDAO")
    private EventoDAO eventoDAO;
    
    @Autowired
    @Qualifier("entidadDAO")
    private EntidadDAO entidadDAO;
 
    
    public GestorCompraTickets(){}
    
    
   
    //---------------- EXCEPCIONES ---------------------
    
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ExcepcionRecinto.class})
    public void handlerErrorRecinto(){}
    
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ExcepcionObjetoRepetido.class})
    public void handlerErrorObjetoRepetido(){}
    
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ExcepcionObjetoNoExiste.class})
    public void handlerErrorObjetoNoExiste(){}
    
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ExcepcionTicket.class})
    public void handlerErrorTicket(){}
    
   
    //-----------------------------------------RECURSOS--------------------------------------
    /*    
        METODOS PARA EL CUALQUIER TIPO DE ROL    
        Devuelven lista con todos los eventos 
    */
    @RequestMapping(value = "Recurso/Evento",method = RequestMethod.GET,produces = "application/json; charset=utf8")
    public List<EventoDTO> getListaEventos(){      
        
        List<EventoDTO> listaEventos=new ArrayList<>();
        Calendar calendarAux;
        
        for(Evento evento:this.eventoDAO.getTodosEventos()){
            
            calendarAux=evento.getFecha();
            EventoDTO eventoDTO=new EventoDTO(evento.getNombre(),calendarAux.get(Calendar.YEAR),calendarAux.get(Calendar.MONTH)+1,calendarAux.get(Calendar.DAY_OF_MONTH),
                                               evento.getRecinto().getLocalidad(),evento.getRecinto().getDireccion(),evento.getTipo().toString());
                        
            listaEventos.add(eventoDTO);
        }
        
        return listaEventos;        
    }    
        
    /*
        Devuelven lista con todos los eventos indicado por el nombre 
    */
    @RequestMapping(value = "Recurso/Evento/{nombreEvento}",method = RequestMethod.GET,produces = "application/json; charset=utf8")
    public List<EventoDTO> getEventos(@PathVariable String nombreEvento){      
        
        List<EventoDTO> listaEventos=new ArrayList<>();
        Calendar calendarAux;
        
        for(Evento evento:this.eventoDAO.Buscar(nombreEvento)){
            
            calendarAux=evento.getFecha();
            EventoDTO eventoDTO=new EventoDTO(evento.getNombre(),calendarAux.get(Calendar.YEAR),calendarAux.get(Calendar.MONTH)+1,calendarAux.get(Calendar.DAY_OF_MONTH),
                                               evento.getRecinto().getLocalidad(),evento.getRecinto().getDireccion(),evento.getTipo().toString());
                        
            listaEventos.add(eventoDTO);
        }
        
        return listaEventos;        
    }    
    
    
    /*
        Dado un evento muestra las zonas y el estado en el que se encuentran
    */
    @RequestMapping(value = "Recurso/Evento/{nombreEvento}/Zona/Estado",method = RequestMethod.GET,produces = "application/json; charset=utf8")
    public List<EstadoRecintoDTO> getEstadoZonas(@PathVariable String nombreEvento) throws ExcepcionRecinto{
        
        List<EstadoRecintoDTO> listaRecintos=new ArrayList<>();    
        
        //Busco los eventos generados con el nombre introducido
        for(Evento evento: this.eventoDAO.Buscar(nombreEvento)){     
            
            List<ZonaDTO> listaZonas=new ArrayList<>();
            
            //Busco las zonas que tiene cada evento
            for(Zona zona:evento.getRecinto().getMapaZonas().values()){
                
                
                List<FilaDTO> listaFilas=new ArrayList<>(); 
                
                //De cada zona, voy guardando los asientos que hay en cada fila               
                for(List<Boolean> listaAsientos:evento.getMapaEstadoZonas(zona.getNombre())){
                    
                    listaFilas.add(new FilaDTO(listaAsientos));
                }
                //Guardo el estado de la zona, conjunto total de filas y sus asientos con su estado
                listaZonas.add(new ZonaDTO(zona.getNombre(),listaFilas));
                
                            
            }
            //Almaceno el estado del recinto, es decir, el conjunto de todas las zonas
            listaRecintos.add(new EstadoRecintoDTO(evento.getNombre(),evento.getFecha().get(Calendar.YEAR),
                                                                    evento.getFecha().get(Calendar.MONTH)+1,
                                                                    evento.getFecha().get(Calendar.DAY_OF_MONTH),listaZonas)); 
             
        }
        
        return listaRecintos;
    }    
    
     
    /*
        Devuelve las zonas del evento 
    */
    @RequestMapping(value = "Recurso/Evento/{nombreEvento}/Zona",method = RequestMethod.GET,produces = "application/json; charset=utf8")
    public List<EstadoRecintoDTO> getZonasEvento(@PathVariable String nombreEvento)
            throws ExcepcionRecinto{
        
       List<EstadoRecintoDTO> listaRecintos=new ArrayList<>();    
        
        //Busco los eventos generados con el nombre introducido
        for(Evento evento: this.eventoDAO.Buscar(nombreEvento)){     
            
            List<ZonaDTO> listaZonas=new ArrayList<>();
            
            //Busco las zonas que tiene cada evento
            for(Zona zona:evento.getRecinto().getMapaZonas().values()){
                  
                //Solo almaceno el nombre
                listaZonas.add(new ZonaDTO(zona.getNombre()));
                 
            }
            //Almaceno el estado del recinto, es decir, el conjunto de todas las zonas
            listaRecintos.add(new EstadoRecintoDTO(evento.getNombre(),evento.getFecha().get(Calendar.YEAR),
                                                                    evento.getFecha().get(Calendar.MONTH)+1,
                                                                    evento.getFecha().get(Calendar.DAY_OF_MONTH),listaZonas)); 
             
        }
        
        return listaRecintos;
        
    }
       
      
    @RequestMapping(value = "Recurso/Usuario",method = RequestMethod.POST,consumes = "application/json")    
    public ResponseEntity<Boolean> nuevoUsuario(@RequestBody EntidadDTO entidadDTO)   
            throws ExcepcionObjetoRepetido{
        
        try {
            
            System.out.println("Nombre " + entidadDTO.getIdentificador() + " password " + entidadDTO.getPassword());
            
            this.entidadDAO.Insertar(new Entidad(entidadDTO.getIdentificador(),entidadDTO.getPassword(),"ROLE_USER"));
            return new ResponseEntity<>(true,HttpStatus.CREATED);
            
        } catch (ExcepcionObjetoRepetido | RuntimeException ex) {
            throw new ExcepcionObjetoRepetido(ex.getMessage());
        }
    }
    
    
    @RequestMapping(value = "Recurso/Rol", method = RequestMethod.GET, produces = "application/json; charset=utf8")
    public List<String> getRol() {
        
        List < String > roles = new ArrayList();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        
        return roles;
        
    }
    
    
     //-----------------------------------------ADMINISTRADOR--------------------------------------
    /*
    
                METODOS PARA EL ADMINISTRADOR
    
    */
    
    @RequestMapping(value = "Admin/Evento",method =RequestMethod.POST,consumes = "application/json")     
    public ResponseEntity<Boolean> nuevoEvento(@RequestBody EventoDTO eventoDTO) 
            throws ExcepcionObjetoRepetido,ExcepcionRecinto{
      
       try{            
           Recinto recinto=this.recintoDAO.Buscar(eventoDTO.getLocalidad(), eventoDTO.getDireccion());
          
           if(recinto==null)
               throw new ExcepcionRecinto("Recinto no existe");
           
           
           Evento evento=new Evento(recinto,eventoDTO.getNombre(), Evento.CategoriaEvento.valueOf(eventoDTO.getCategoria()),
                                    new GregorianCalendar(eventoDTO.getAnio(),eventoDTO.getMes(),eventoDTO.getDia()));
           
           this.eventoDAO.Insertar(evento);   
           return new ResponseEntity<>(true,HttpStatus.CREATED);
           
       }catch(ExcepcionObjetoRepetido e){
           throw new ExcepcionObjetoRepetido(e.getMessage());
       }catch(ExcepcionRecinto e){
           throw new ExcepcionRecinto(e.getMessage());
       }       
    }
    
   
    @RequestMapping(value = "Admin/Evento", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> borrarEvento(@RequestParam (name = "nombre", required = true) String nombre,
                                                @RequestParam (name = "anio", required = true) int anio,
                                                @RequestParam (name = "mes", required = true) int mes,
                                                @RequestParam (name = "dia", required = true) int dia
    ) 
            throws ExcepcionObjetoNoExiste {
       
        try{ 
            this.eventoDAO.Borrar(this.eventoDAO.Buscar(new GregorianCalendar(anio,mes,dia), nombre));
            return new ResponseEntity<>(true,HttpStatus.OK);
            
        } catch (ExcepcionObjetoNoExiste ex){
            throw new ExcepcionObjetoNoExiste(ex.getMessage());
        }
    }
    

    
    /*
        Devuelve la lista de recintos que hay en una localidad
    */
    @RequestMapping(value = "Admin/Recinto/{localidad}",method = RequestMethod.GET,produces = "application/json; charset=utf8")
    public List<RecintoDTO> getListaRecintos (@PathVariable String localidad){
        
        List<RecintoDTO> listaRecintos=new ArrayList<>();
         
        for(Recinto recinto:this.recintoDAO.getListaRecintos(localidad)){
            
            listaRecintos.add(new RecintoDTO(recinto.getLocalidad(),recinto.getDireccion()));
        }
        
        return listaRecintos;
    }  
       
    
     //-----------------------------------------USUARIO--------------------------------------
    /*
    
                METODOS PARA EL USUARIO
    
    */    
    @RequestMapping(value = "User/Ticket",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<TicketDTO> comprarTicket(@RequestBody TicketDTO ticketDTO) 
                 throws ExcepcionObjetoNoExiste, ExcepcionTicket, ExcepcionZona {

        try {            
             
            Evento eventoAux = this.eventoDAO.Buscar(new GregorianCalendar(ticketDTO.getAnio(),ticketDTO.getMes(),ticketDTO.getDia())
                                                     ,ticketDTO.getNombreEvento());
            
            Ticket ticketAux=eventoAux.comprarTicket(ticketDTO.getNombreZona(), ticketDTO.getFila(), ticketDTO.getAsiento(),ticketDTO.getID_Usuario());
           
            //El ID que se genera al guardar el ticket, se le devuelve al usuario
            ticketDTO.setID_Ticket(ticketAux.getCodigo());
            
            this.eventoDAO.Actualizar(eventoAux);     
            
            return new ResponseEntity<>(ticketDTO,HttpStatus.OK);
            
        } catch (ExcepcionObjetoNoExiste ex) {
            throw new ExcepcionObjetoNoExiste(ex.getMessage());
        } catch (ExcepcionTicket ex) {
            throw new ExcepcionTicket(ex.getMessage());
        } catch (ExcepcionZona ex) {
            throw new ExcepcionZona(ex.getMessage());
        }
}
    
    @RequestMapping(value = "User/Ticket",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> anularTicket(@RequestParam (name = "nombre", required = true) String nombre,
                                                @RequestParam (name = "anio", required = true) int anio,
                                                @RequestParam (name = "mes", required = true) int mes,
                                                @RequestParam (name = "dia", required = true) int dia,
                                                @RequestParam (name = "id_ticket", required = true) int id_ticket,
                                                @RequestParam (name = "id_user", required = true) String id_user
            )
                throws ExcepcionObjetoNoExiste,ExcepcionTicket{
        try{            
            Evento eventoAux = this.eventoDAO.Buscar(new GregorianCalendar(anio,mes,dia), nombre);
            eventoAux.anularTicket(id_ticket, id_user);
            this.eventoDAO.Actualizar(eventoAux);
        
            return new ResponseEntity<>(true,HttpStatus.OK);
            
        }catch(ExcepcionObjetoNoExiste e){
            throw new ExcepcionObjetoNoExiste(e.getMessage());
        }catch(ExcepcionTicket e){
            throw new ExcepcionTicket(e.getMessage());
        }
        
    }    
    
    /*
        Devuelve lo tickets de un usuario
    */
    @RequestMapping(value = "User/Ticket/{usuario}", method = RequestMethod.GET, produces = "application/json")
    public List<TicketDTO> consultarTicket(@PathVariable String usuario) 
            throws ExcepcionObjetoNoExiste {        
    
        try {
            
            
            List<TicketDTO> listaTickets=new ArrayList<>();
            List<Evento> listaEventos = new ArrayList<>();
            
            listaEventos = eventoDAO.getTodosEventos();
            
            for (Evento e : listaEventos){
                for (Ticket ticket : e.getTicketsUsuario(usuario).values()){
                    listaTickets.add(new TicketDTO (ticket.getNombreEvento(), ticket.getNombreZona(), 
                            ticket.getFila(), ticket.getAsiento(), usuario, ticket.getCodigo(), ticket.isAnulado()));
                    
                }
            }
            
            return listaTickets;

        } catch (ExcepcionObjetoNoExiste ex) {
            throw new ExcepcionObjetoNoExiste (ex.getMessage());
        }
    }            
    
    

     
}
