/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domingo_Reto3.Reto3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosReservaciones {
     @Autowired
    private RepositorioReservaciones metodosCrud4;

    public List<Reservaciones> getAll(){
        return metodosCrud4.getAll();
    }

    public Optional<Reservaciones> getReservation(int reservationId) {
        return metodosCrud4.getReservation(reservationId);
    }

    public Reservaciones save(Reservaciones reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud4.save(reservation);
        }else{
            Optional<Reservaciones> e= metodosCrud4.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud4.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservaciones update(Reservaciones reservaciones){
        if(reservaciones.getIdReservation()!=null){
            Optional<Reservaciones> e= metodosCrud4.getReservation(reservaciones.getIdReservation());
            if(!e.isEmpty()){

                if(reservaciones.getStartDate()!=null){
                    e.get().setStartDate(reservaciones.getStartDate());
                }
                if(reservaciones.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservaciones.getDevolutionDate());
                }
                if(reservaciones.getStatus()!=null){
                    e.get().setStatus(reservaciones.getStatus());
                }
                metodosCrud4.save(e.get());
                return e.get();
            }else{
                return reservaciones;
            }
        }else{
            return reservaciones;
        }
    }

    public boolean deleteReservation(int id) {
        Boolean aBoolean = getReservation(id).map(reservation -> {
            metodosCrud4.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    
    public StatusReservas reporteStatusServicio (){
        List<Reservaciones>completed= metodosCrud4.ReservacionStatusRepositorio("completed");
        List<Reservaciones>cancelled= metodosCrud4.ReservacionStatusRepositorio("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size() );
    }
    
    public List<Reservaciones> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
        
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
             datoUno = parser.parse(datoA);
             datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud4.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        
        } 
    }
    
    public List<ContadorClientes> reporteClientesServicio(){
            return metodosCrud4.getClientesRepositorio();
        }
}
