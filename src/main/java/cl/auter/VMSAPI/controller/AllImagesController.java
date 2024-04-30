package cl.auter.VMSAPI.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<MessageImageAllModel> allImages() {
		List<MessageImageAllModel> images = messageImageAllService.getMessageImageAllModel();
				
		return images;	
	}
}
