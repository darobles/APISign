package cl.auter.VMSAPI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.auter.util.DecodeJwt;

@Component
public class NotificationsTimer {
	
	private final MaintenancesRepository mantRep;
	private final NotificationsRepository notRep;
	private       DecodeJwt     decJwt  = new DecodeJwt();
	private       LocalDate     regDate;
	private       long          diff;
	
	@Autowired
    private EmailService es;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	@Autowired
	public NotificationsTimer(MaintenancesRepository mantRep, NotificationsRepository notRep) {
		this.mantRep = mantRep;
		this.notRep  = notRep;
	}
	
	@PostConstruct
    public void startScheduledTask() {
        scheduler.scheduleAtFixedRate(() -> {
        	verifyMaintenances();
        }, 0, 43200, TimeUnit.SECONDS); // se ejecuta cada 12 horas
    }

    public void stopScheduledTask() {
        scheduler.shutdown();
    }
    
    public void verifyMaintenances() {
    	LocalDate                fechaActual = LocalDate.now();
        DateTimeFormatter        formatter   = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<MaintenancesEntity> nextMant    = new ArrayList();
        LocalDate                auxDate;
        long                     nextDiff    = 999999999;
        long                     finalDiff   = 0;
    	try {
    		List<MaintenancesEntity> me = mantRep.findAll();
    		for ( int i = 0 ; i < me.size() ; i ++ ) {
    			if ( me.get(i).getNotified().equals("no") ) {
    				regDate = LocalDate.parse(me.get(i).getDate(), formatter);
	    			diff    = calcularDiferenciaEnDias(fechaActual, regDate);
	    			if ( diff <= 7 ) {
	    				
	    				String destinatario = me.get(i).getEmail() + "";
	    		        String asunto = "Alerta de mantención";
	    		        String cuerpo = "Estimado usuario\n\nMediante este correo le notificamos que tiene una mantención agendada "
	    		        		+ "con fecha " + me.get(i).getDate() + ".\n\n"
	    		        		+ "Los detalles son:"
	    		        		+ "\n	- Ubicación: " + me.get(i).getLocation()
	    		        		+ "\n	- Responsable: " + me.get(i).getResponsable()
	    		        		+ "\n	- Observación: " + me.get(i).getObservation()
	    		        		//+ "\n\nAtentamente, el equipo de Auter."
	    		        		+ "\n\n\nEste correo es automático, por favor no responder.";

	    		        es.enviarCorreo(destinatario, asunto, cuerpo);
	    		        
	    		        MaintenancesEntity newMe = new MaintenancesEntity();
	    		        
	    		        newMe.setId(me.get(i).getId());
	    		        newMe.setDate(me.get(i).getDate());
	    		        newMe.setEmail(me.get(i).getEmail());
	    		        newMe.setLocation(me.get(i).getLocation());
	    		        newMe.setNotified("yes");
	    		        newMe.setObservation(me.get(i).getObservation());
	    		        newMe.setResponsable(me.get(i).getResponsable());
	    		        newMe.setStatus(me.get(i).getStatus());
	    		        newMe.setType(me.get(i).getType());
	    		        newMe.setVms_code(me.get(i).getVms_code());
	    		        newMe.setNotifiations_sended(me.get(i).getNotifiations_sended());
	    		        
	    		        mantRep.save(newMe);
	    		        
	    			}

    			}
    			
    			if ( me.get(i).getNotifiations_sended().equals("no") ) {
    				auxDate = LocalDate.parse(me.get(i).getDate(), formatter);
    				long auxDiff = calcularDiferenciaEnDias(fechaActual, auxDate);
    				if ( auxDiff < nextDiff && auxDiff > 0 ) {
    					nextDiff  = auxDiff;
    					finalDiff = nextDiff;
    				}
    			}
	    			
    		}
    		
    		for ( int i = 0 ; i < me.size() ; i ++ ) {
    			if ( me.get(i).getNotifiations_sended().equals("no") ) {
    				auxDate = LocalDate.parse(me.get(i).getDate(), formatter);
    				long auxDiff = calcularDiferenciaEnDias(fechaActual, auxDate);
    				if ( auxDiff == finalDiff ) {
    					NotificationsEntity ne = new NotificationsEntity();
    					
    					ne.setClicked("no");
    					ne.setDescription("Mantención en "
    							+ finalDiff + " dias: "
    							+ me.get(i).getDate()
    							+ ", \"" + me.get(i).getObservation()
    							+ "\". Responsable: " + me.get(i).getResponsable());
    					ne.setMaintenance_id(me.get(i).getId());
    					ne.setShowed("no");
    					ne.setUsername(me.get(i).getResponsable());
    					
    					notRep.save(ne);
    					
    					MaintenancesEntity newMant = me.get(i);
    					
    					newMant.setNotifiations_sended("yes");
    					
    					mantRep.save(newMant);
    				}
    			}
    		}
    		
    		
	    	
    		List<NotificationsEntity> ne = notRep.findAll();
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
   	
    }
    
    public long calcularDiferenciaEnDias(LocalDate fechaInicio, LocalDate fechaFin) {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
}
