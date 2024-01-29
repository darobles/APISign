package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.VMSViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.VMSAPI.protocol.DIANMINGInfo;
import cl.auter.VMSAPI.service.VMSViewService;
import cl.auter.util.VMSUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sign")
public class VMSViewController {
	@Autowired
	private  VMSViewService vmsService;
	
	@GetMapping("")
	public List<VMSViewModel> findAll(){
		List<VMSViewModel> vmsList = vmsService.findAll();
		return vmsList;
		
		
	}
	
	@GetMapping("/{id}")
	public JSONObject findById(@PathVariable("id") Integer vms_id){
		JSONObject outputJSON = new JSONObject();
	        try {
	        	VMSViewModel sign = vmsService.findVMSById(vms_id);
	            
	            outputJSON.put("id", sign.getId_letrero());
	            outputJSON.put("name", sign.getNombre()); 
	            outputJSON.put("latitude", sign.getLatitud()); 
	            outputJSON.put("longitude", sign.getLongitud()); 
	            outputJSON.put("location", sign.getUbicacion()); 
	            outputJSON.put("status", 1); 
	            outputJSON.put("signTypeId", sign.getId_tipo_letrero()); 
	            outputJSON.put("signTypeName", sign.getNombre_tipo_letrero()); 
	            outputJSON.put("protocolId", sign.getCodificacion()); 
	            outputJSON.put("protocolName", "Nombre"); 
	            outputJSON.put("obs", sign.getObs()); 
	            outputJSON.put("connectionId", sign.getId_conexion()); 
	            outputJSON.put("connectionName", sign.getNombre_conexion()); 
	            outputJSON.put("phoneOrIP", sign.getFono()); 
	            outputJSON.put("key", sign.getClave());
	            outputJSON.put("portCOM", sign.getCanal()); 
	            outputJSON.put("address", sign.getDireccion()); 
	            outputJSON.put("port", sign.getPort()); 
	            outputJSON.put("camera", sign.getCamara()); 
	            outputJSON.put("cameraType", sign.getCamera_type()); 
	            
	            if (sign.getCodificacion() == 1) { //Diangming
	                JSONArray objArray = new JSONArray();
	                DIANMING  dianming = new DIANMING(sign);
	                dianming.setAddresses(sign.getDireccion());
	                List<DIANMINGInfo> cabinets = dianming.getDetailedStatus();
	                //socket.close();
	                int idGabinete = 0;
	                for (DIANMINGInfo cabinet : cabinets) {
	                    idGabinete ++;
	                    JSONObject outputJSONGabinete = new JSONObject();
	                    outputJSONGabinete.put("cabinetId", idGabinete);
	                    outputJSONGabinete.put("cabinetName", "Cabinet" + VMSUtils.ZeroPad(idGabinete, 2));
	                    outputJSONGabinete.put("temperature", cabinet.getTemperature());
	                    outputJSONGabinete.put("voltage1", cabinet.getVoltage1());
	                    outputJSONGabinete.put("voltage2", cabinet.getVoltage2());
	                    outputJSONGabinete.put("doorSwitch1", cabinet.getDoorSwitch1() ? "Open" : "Close");
	                    outputJSONGabinete.put("doorSwitch2", cabinet.getDoorSwitch2() ? "Open" : "Close");
	                    outputJSONGabinete.put("brightness", cabinet.getBrightness());
	                    outputJSONGabinete.put("photosensitive1", cabinet.getPhotosensitive1());
	                    outputJSONGabinete.put("photosensitive2", cabinet.getPhotosensitive2());
	                    
	                    objArray.put(outputJSONGabinete);
	                }
	                outputJSON.put("cabinets", objArray);
	            }
	        } catch (Exception ex) {
	            //outputJSON.clear();
	            outputJSON.put("error", ex.toString());
	        }
	        return outputJSON;
		
	}
	
}
