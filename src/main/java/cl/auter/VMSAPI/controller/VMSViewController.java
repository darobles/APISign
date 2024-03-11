package cl.auter.VMSAPI.controller;

import java.util.List;

import javax.json.JsonObject;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.BrightnessEntity;
import cl.auter.VMSAPI.model.Cabinet;
import cl.auter.VMSAPI.model.GrupoModel;
import cl.auter.VMSAPI.model.LetreroComModel;
import cl.auter.VMSAPI.model.SequenceViewModel;
import cl.auter.VMSAPI.model.MessageImage;
import cl.auter.VMSAPI.model.MessagePreviewModel;
import cl.auter.VMSAPI.model.SideImage;
import cl.auter.VMSAPI.model.SideImageModel;
import cl.auter.VMSAPI.model.SignModel;
import cl.auter.VMSAPI.model.SymbolModel;
import cl.auter.VMSAPI.model.view.SignMessageViewModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.model.view.VMSViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.VMSAPI.protocol.DIANMINGInfo;
import cl.auter.VMSAPI.service.GroupService;
import cl.auter.VMSAPI.service.MessageService;
import cl.auter.VMSAPI.service.MessageViewService;
import cl.auter.VMSAPI.service.SequenceViewService;
import cl.auter.VMSAPI.service.SignComService;
import cl.auter.VMSAPI.service.SignMessageViewService;
import cl.auter.VMSAPI.service.SignService;
import cl.auter.VMSAPI.service.SignTypeViewService;
import cl.auter.VMSAPI.service.SymbolService;
import cl.auter.VMSAPI.service.VMSViewService;
import cl.auter.util.Constants;
import cl.auter.util.VMSUtils;
import cl.auter.VMSAPI.model.VMSResponseEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sign")
public class VMSViewController {
	@Autowired
	VMSViewService vmsService;	
	@Autowired
	SignTypeViewService signTypeViewService;
	@Autowired
	SignMessageViewService signMessageViewService;	
	@Autowired
	MessageViewService messageViewService;	
	@Autowired
	MessageService messageService;	
	@Autowired
	SequenceViewService sequenceViewService;	
	@Autowired
	SymbolService symbolService;	
	@Autowired
	SignService signService;	
	@Autowired
	SignComService signComService;
	@Autowired
	GroupService groupService;

	@GetMapping("")
	public List<VMSViewModel> findAll(){
		List<VMSViewModel> vmsList = vmsService.findAll();
		for(VMSViewModel sign: vmsList)
		{
            if (sign.getCodificacion() == 1) { //Diangming
                DIANMING  dianming = new DIANMING(sign);
                dianming.setAddresses(sign.getDireccion());
                List<DIANMINGInfo> cabinetsInfo = dianming.getDetailedStatus();
                //socket.close();
                List<Cabinet> cabinets = new ArrayList<Cabinet>();
                int idGabinete = 0;
                for (DIANMINGInfo cabinet : cabinetsInfo) {
                	Cabinet cabinetAux = new Cabinet();
                    idGabinete ++;
                    cabinetAux.setCabinetId(idGabinete);
                    cabinetAux.setCabinetName("Cabinet" + VMSUtils.ZeroPad(idGabinete, 2));
                    cabinetAux.setTemperature(cabinet.getTemperature());
                    cabinetAux.setVoltage1(cabinet.getVoltage1());
                    cabinetAux.setVoltage2(cabinet.getVoltage2());
                    cabinetAux.setDoorSwitch1(cabinet.getDoorSwitch1());
                    cabinetAux.setDoorSwitch2(cabinet.getDoorSwitch2());
                    cabinetAux.setBrightness(cabinet.getBrightness());
                    cabinetAux.setPhotosensitive1(cabinet.getPhotosensitive1());
                    cabinetAux.setPhotosensitive2(cabinet.getPhotosensitive2());
                    
                    cabinets.add(cabinetAux);
                }
                sign.setCabinets(cabinets);
            }
			
		}
		return vmsList;
	}
	
	@GetMapping("/{id}")
	public VMSViewModel findById(@PathVariable("id") Integer vms_id){
		VMSViewModel sign = vmsService.findVMSById(vms_id);
		   try {	        	
	            if (sign.getCodificacion() == 1) { //Diangming
	                DIANMING  dianming = new DIANMING(sign);
	                dianming.setAddresses(sign.getDireccion());
	                List<DIANMINGInfo> cabinetsInfo = dianming.getDetailedStatus();
	                //socket.close();
	                List<Cabinet> cabinets = new ArrayList<Cabinet>();
	                int idGabinete = 1;
	                for (DIANMINGInfo cabinet : cabinetsInfo) {
	                	Cabinet cabinetAux = new Cabinet();
	                    
	                    cabinetAux.setCabinetId(idGabinete);
	                    cabinetAux.setCabinetName("Cabinet" + VMSUtils.ZeroPad(idGabinete, 2));
	                    cabinetAux.setTemperature(cabinet.getTemperature());
	                    cabinetAux.setVoltage1(cabinet.getVoltage1());
	                    cabinetAux.setVoltage2(cabinet.getVoltage2());
	                    cabinetAux.setDoorSwitch1(cabinet.getDoorSwitch1());
	                    cabinetAux.setDoorSwitch2(cabinet.getDoorSwitch2());
	                    cabinetAux.setBrightness(cabinet.getBrightness());
	                    cabinetAux.setPhotosensitive1(cabinet.getPhotosensitive1());
	                    cabinetAux.setPhotosensitive2(cabinet.getPhotosensitive2());	                    
	                    cabinets.add(cabinetAux);
	                    idGabinete ++;
	                }
	                sign.setCabinets(cabinets);
	            }
	        } catch (Exception ex) {
	            //outputJSON.clear();
	        }	      
	        return sign;	
	}
	
	@GetMapping("/{id}/message")
	public List<SignMessageViewModel> getJson(@PathVariable("id") int idSign) {

        List<SignMessageViewModel> messages = signMessageViewService.findAllBySignId(idSign);
        return messages;
    }
	
	@GetMapping("/{id}/groups")
	public List<GrupoModel> getGroups(@PathVariable("id") int idSign) {

        //List<GrupoModel> messages = groupService.findAllById(idSign);
        return new ArrayList();
    }
	
	@GetMapping("/{id}/brightness")
	public BrightnessEntity getBrightness(@PathVariable("id") int idSign) {

        //List<GrupoModel> messages = groupService.findAllById(idSign);
		BrightnessEntity demo = new BrightnessEntity();
		demo.setAutomatic(false);
		demo.setValue(45);
        return demo;
    }
	
	@PutMapping("/{id}/brightness/{value}")
	public VMSResponseEntity updateBrightness(@PathVariable("id") int idSign,@PathVariable("id") String brightnessValue) {

		VMSResponseEntity response  = new VMSResponseEntity();
		response.setStatus(500);
        try {
            if ((brightnessValue.compareToIgnoreCase("FF") == 0) || (brightnessValue.compareToIgnoreCase("AUTO") == 0)) {
                brightnessValue = "255";
            }
            VMSViewModel sign = vmsService.getById(idSign);
            
            if (sign.getCodificacion() == Constants.ID_DIANMING) {
                Integer brightness = Integer.valueOf(brightnessValue);
                if (((brightness < 0) || (brightness >= 32)) && (brightness != 255)) {
                	response.setMessage("Brightness value out of range");
                }
            
                if (response.getMessage().equals("")) {
                	Thread t1 = new Thread(new Runnable() {
                	    @Override
                	    public void run() {
                            DIANMING dianming = new DIANMING(sign);
                            dianming.setBrightness(brightness);
                	    }
                	});  
                	t1.start();

                }
            } else {
            	response.setMessage("VMS is not DIANMING (protocol: " + sign.getProtocol_name() + ")");
            }           
            
        } catch (Exception ex) {
        	response.setMessage(ex.toString());
        }
        
        if (response.getMessage().equals("")) {
        	response.setMessage("success");
        	response.setStatus(200);
        }
        return response;
    }
	 	
	
		@GetMapping("/{idSign}/sequence")
	    public List<SequenceViewModel> getSequence(@PathVariable("idSign") int idSign) {
			List<SequenceViewModel> sequences = sequenceViewService.findByIdVMS(idSign);	        
	        return sequences;
	    }

	    /**
	     * PUT method for updating or creating an instance of VMSRestSignSequenceList
	     * @param idSign resource URI parameter
	     * @param content representation for the resource
	     */
	    @PutMapping("/{idSign}/sequence")
	    public String putJson(@PathVariable("idSign") String idSign, String content) {
	       /* JSONParser parser     = new JSONParser();
	        JSONObject inputJSON;
	        JSONObject outputJSON = new JSONObject();

	        try {
	            inputJSON = (JSONObject) parser.parse(content);

	            Sequence sequence = new Sequence();
	            sequence.setName((String) inputJSON.get("name"));
	            
	            try {	                
	                Integer idSequence = dao.addSignSequence(Integer.valueOf(idSign), sequence);
	                if (idSequence > 0) {
	                    outputJSON.put("sequenceId", idSequence);
	                } else {
	                    outputJSON.put("error", "Sequence could not be created");
	                }
	            } catch (Exception ex) {
	                outputJSON.put("error", ex.toString());
	            }
	        } catch (Exception ex) {
	            outputJSON.put("error", ex.toString());
	        }
	        
	        return outputJSON.toJSONString();*/
	    	return "";
	    }
	    
	    @PutMapping("/{idSign}")
	    public ResponseEntity<VMSResponseEntity> editSign(@PathVariable("idSign") Integer id, @RequestBody VMSViewModel vms) {
	    	VMSResponseEntity response = new VMSResponseEntity();
	    	System.out.println(vms.toString());
	    	LetreroComModel letreroCom = new LetreroComModel();
	    	letreroCom.setId_letrero(vms.getId_letrero());
	    	letreroCom.setId_conexion(vms.getId_conexion());	    	
	    	letreroCom.setFono(vms.getFono());
	    	letreroCom.setClave(vms.getClave());
	    	letreroCom.setCanal(vms.getCanal());
	    	letreroCom.setDireccion(vms.getDireccion());
	    	letreroCom.setPort(vms.getPort());
	    	letreroCom.setId_camara(vms.getId_camara());
	    	signComService.save(letreroCom);
	    	SignModel sign = new SignModel();
	    	sign.setId_letrero(vms.getId_letrero());
	    	sign.setId_tipo_letrero(vms.getId_tipo_letrero());
	    	sign.setUbicacion(vms.getUbicacion());
	    	sign.setLatitud(vms.getLatitud());
	    	sign.setLongitud(vms.getLongitud());
	    	sign.setNombre(vms.getNombre());
	    	sign.setObs(vms.getObs());
	    	signService.save(sign);
	    	return ResponseEntity.ok(response);
	    }
	    
	    
	    @DeleteMapping("/{idSign}")
	    public ResponseEntity<VMSResponseEntity> deleteSign(@PathVariable("idSign") Integer idSign) {
	    	SignModel sign = signService.getById(idSign);
	    	VMSResponseEntity response = new VMSResponseEntity();
	    	if(sign != null) {
		    	signService.deleteById(sign.getId_letrero());
		    	messageService.deleteByType(sign.getId_tipo_letrero());
		    	response.setStatus(200);
		    	response.setMessage("ok");
	    	}
	    	else {
		    	response.setStatus(501);
		    	response.setMessage("Id no existe");
	    	}

	    	return ResponseEntity.ok(response);
	    }
	    
}
