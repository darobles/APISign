package cl.auter.VMSAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.service.SignTypeViewService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/signType")
public class SignTypeViewController {

	@Autowired
	SignTypeViewService signTypeViewService;
	
	@GetMapping("")
	public List<SignTypeViewModel> findAll(){
		List<SignTypeViewModel> vmsList = signTypeViewService.findAll();
		return vmsList;
		
		
	}
	
	
}
