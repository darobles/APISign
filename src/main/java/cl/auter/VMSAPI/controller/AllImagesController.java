package cl.auter.VMSAPI.controller;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.MessageImageAllModel;
import cl.auter.VMSAPI.service.MessageImageAllService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/allimages")
public class AllImagesController {
	@Autowired
	MessageImageAllService messageImageAllService;
	
	@GetMapping("")
	public List<MessageImageAllModel> allImages(@RequestBody MessageImageAllModel jsonInput) {
		List<MessageImageAllModel> images;
		
		if ((jsonInput != null) && (jsonInput.getNombre_mensaje() != null) && (! jsonInput.getNombre_mensaje().equals(""))) {
			// Filter by message name
			String messageName = "%" + jsonInput.getNombre_mensaje().replaceAll("%", "\\\\%") + "%";
			images = messageImageAllService.getMessageImageModelByName(messageName);
		} else {
			images = messageImageAllService.getMessageImageAllModel();
		}
		
		return images;	
	}
}
