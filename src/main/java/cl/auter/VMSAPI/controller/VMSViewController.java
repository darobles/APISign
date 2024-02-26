package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.Cabinet;
import cl.auter.VMSAPI.model.MessageImage;
import cl.auter.VMSAPI.model.MessagePreviewModel;
import cl.auter.VMSAPI.model.SideImage;
import cl.auter.VMSAPI.model.SideImageModel;
import cl.auter.VMSAPI.model.SymbolModel;
import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.model.view.SignMessageViewModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.model.view.VMSViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.VMSAPI.protocol.DIANMINGInfo;
import cl.auter.VMSAPI.security.config.SignMessageViewService;
import cl.auter.VMSAPI.service.MessageViewService;
import cl.auter.VMSAPI.service.SignTypeViewService;
import cl.auter.VMSAPI.service.SymbolService;
import cl.auter.VMSAPI.service.VMSViewService;
import cl.auter.util.VMSUtils;

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
	SymbolService symbolService;

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
	 
	@PostMapping("/{id}/message")
	public String getMessageImage(@PathVariable("id") int idSign, @RequestBody MessagePreviewModel json) {
		JSONObject outputJSON = new JSONObject();
		try {
			SideImageModel          simLeft  = null;
			SideImageModel          simRight = null;
			String                  message  = json.getMessage();
			VMSViewModel            vms      = vmsService.getById(idSign);
			List<SignTypeViewModel> st       = signTypeViewService.findAllBySignTypeId(vms.getId_tipo_letrero());
			
			if ((st == null) || st.isEmpty()) {
				throw new Exception("No sign type defined for the given VMS");
			}
			
			List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(json.getGroupId(), VMSUtils.CharsAsStringList(message));
			if ((json.getImageB64_left() != null) && (json.getVerticalAlign_left() != null)) {
				simLeft = new SideImageModel();
				simLeft.setUbicacion_hrz(0);
				simLeft.setUbicacion_vrt(json.getVerticalAlign_left());
				simLeft.setImagen_b64(json.getImageB64_left());
			}
			if ((json.getImageB64_right() != null) && (json.getVerticalAlign_right() != null)) {
				simRight = new SideImageModel();
				simRight.setUbicacion_hrz(1);
				simRight.setUbicacion_vrt(json.getVerticalAlign_right());
				simRight.setImagen_b64(json.getImageB64_right());
			}
			
			MessageImage mi = new MessageImage(st.get(0), json.getAlignmentId(), json.getColour(), json.getSpacing(), json.getMessage());
			mi.setSymbols(symbolsModel, new SideImage(simLeft), new SideImage(simRight));
        	String b64 = mi.getBase64();
            outputJSON.put("mime", "image/bmp");
            outputJSON.put("data", b64); 
        } catch (Exception ex) {
            outputJSON.put("error", ex.toString());
        }
        return outputJSON.toString();
	}
	
	
}
