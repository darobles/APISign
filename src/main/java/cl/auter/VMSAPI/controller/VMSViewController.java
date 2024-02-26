package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.Cabinet;
import cl.auter.VMSAPI.model.SequenceViewModel;
import cl.auter.VMSAPI.model.view.SignMessageViewModel;
import cl.auter.VMSAPI.model.view.VMSViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.VMSAPI.protocol.DIANMINGInfo;
import cl.auter.VMSAPI.security.config.SignMessageViewService;
import cl.auter.VMSAPI.service.MessageViewService;
import cl.auter.VMSAPI.service.SequenceViewService;
import cl.auter.VMSAPI.service.VMSViewService;
import cl.auter.util.VMSUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sign")
public class VMSViewController {
	@Autowired
	VMSViewService vmsService;
	@Autowired
	SignMessageViewService signMessageViewService;
	
	@Autowired
	MessageViewService messageViewService;
	
	@Autowired
	SequenceViewService sequenceViewService;
	
	@GetMapping("")
	public List<VMSViewModel> findAll(){
		List<VMSViewModel> vmsList = vmsService.findAll();
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
	                List<Cabinet> cabinets = new ArrayList();
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
	 
		@PutMapping("/{id}/message")
	    public JSONArray putJson(@PathVariable("id") int idSign, String content) {
	        /*JSONParser parser     = new JSONParser();
	        JSONObject inputJSON;
	        JSONObject outputJSON = new JSONObject();

	        try {
	            inputJSON = (JSONObject) parser.parse(content);

	            Integer userId = null;
	            try {
	                userId = ((Long) inputJSON.get("userId")).intValue();
	            } catch (Exception ex) {
	                userId = null;
	            }
	            userId = 1;//VMSUtils.UserId(authorization, userId);

	            MessageViewModel message = new MessageViewModel();
	            message.setName((String) inputJSON.get("name"));
	            message.setAlignmentId(((Long) inputJSON.get("alignmentId")).intValue());
	            message.setGroupId(((Long) inputJSON.get("groupId")).intValue());
	            message.setSpacing(((Long) inputJSON.get("spacing")).intValue());
	            message.setColour(((Long) inputJSON.get("textColour")).intValue());
	            message.setMessage((String) inputJSON.get("message"));
	            
	            try {
	                Integer idSignType = dao.getSignTypeFromSign(idSign).getId();
	                message.setSignTypeId(idSignType);
	                
	                int idMessage = dao.addMessage(Integer.valueOf(idSign), message);
	                if (idMessage > 0) {
	                    dao.addLog(idMessage, userId, message.getMessage(), true);
	                    outputJSON.put("entered", true);
	                } else {
	                    outputJSON.put("entered", false);
	                }
	                outputJSON.put("messageId", idMessage);
	            } catch (Exception ex) {
	                outputJSON.put("error", ex.toString());
	            }
	        } catch (Exception ex) {
	            outputJSON.put("error", ex.toString());
	        }
	        
	        return outputJSON;*/
			return null;
	    }
		
		@PutMapping("/{id}/brightness/{value}")
		public ResponseEntity<String> setBrightness(@PathVariable("id") Integer id, @PathVariable("value") Integer brightness){
			
			Thread thread = new Thread(new Runnable() {
			    @Override
			    public void run() {
			    	VMSViewModel vms = vmsService.findVMSById(id);					
			    	DIANMING dianming = new DIANMING(vms);
			    	dianming.setBrightness(brightness);
			    	System.out.println("Done");
			    }
			});
			thread.start();
			return ResponseEntity.ok()
			        .body("done");
			
			
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
	    
}
