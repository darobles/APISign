package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.VMSViewModel;
import cl.auter.VMSAPI.service.VMSViewService;

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
	public Optional<VMSViewModel> findById(@PathVariable("id") Integer vms_id){
		Optional<VMSViewModel> vms = vmsService.findById(vms_id);
		return vms;
		
		
	}
	
}
