package cl.auter.VMSAPI.controller;

import java.util.Base64;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.LastImageEntity;
import cl.auter.VMSAPI.repository.LastImgRepository;
import cl.auter.util.Util;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/last-images")
public class LastImageController {
	
	private final LastImgRepository lastImgRepository;
	
	@Autowired
	public LastImageController(LastImgRepository lastImgRepository) {
		this.lastImgRepository = lastImgRepository;
	}
	
	@GetMapping("/{vms_id}")
	public LastImageEntity findByVmsId(@PathVariable int vms_id){
		return lastImgRepository.findByVmsId(vms_id);
	}
	
	@PostMapping("/")
	public ResponseEntity<JsonObject> saveNewRegister(@RequestBody LastImageEntity json) {
		
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			LastImageEntity newRegister = new LastImageEntity();
			
			newRegister.setDate(json.getDate());
			if ( json.getType().equals("image") ) {
				newRegister.setImage(convertBase64ToBytes(json.getStrImg()));
			}
			else if ( json.getType().equals("sequence") ) {
				newRegister.setImage(null);				
			}
			newRegister.setSequence_id(json.getSequence_id());
			newRegister.setType(json.getType());
			newRegister.setVms_id(json.getVms_id());
			lastImgRepository.save(newRegister);
			jsn.add("result", "success");

		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }
	
	@PutMapping("/")
	public ResponseEntity<JsonObject> editRegister(@RequestBody LastImageEntity json) {
		
		JsonObjectBuilder jsn = Json.createObjectBuilder();
		try {
			LastImageEntity newRegister = new LastImageEntity();
			newRegister.setId(json.getId());
			newRegister.setDate(json.getDate());
			
			if ( json.getType().equals("image") ) {
				newRegister.setImage(convertBase64ToBytes(json.getStrImg()));
			}
			else if ( json.getType().equals("sequence") ) {
				newRegister.setImage(null);
			}
			newRegister.setSequence_id(json.getSequence_id());			
			newRegister.setType(json.getType());
			newRegister.setVms_id(json.getVms_id());
			lastImgRepository.save(newRegister);
			jsn.add("result", "success");

		}
		catch(Exception e) {
			System.out.println(e);
			jsn.add("result", "error");
			jsn.add("detail", e.toString());
		}
		
		return ResponseEntity.ok(jsn.build());
    }
	
	public static byte[] convertBase64ToBytes(String base64Image) {
        String base64ImageWithoutPrefix = base64Image.replaceAll("data:image/[a-zA-Z]+;base64,", "");
        return Base64.getDecoder().decode(base64ImageWithoutPrefix);
    }
}
