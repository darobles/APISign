package cl.auter.VMSAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.SequenceModel;
import cl.auter.VMSAPI.service.SequenceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sequence")
public class SequenceController {
	@Autowired
	SequenceService sequenceService;

	@GetMapping("")
	public List<SequenceModel> findAll(){
		List<SequenceModel> sequences = sequenceService.findAll();
		return sequences;
		
		
	}
	
	@GetMapping("/{id}")
	public SequenceModel findById(@PathVariable("id") Integer id){
		SequenceModel sequence = sequenceService.getById(id);
		return sequence;
		
	}
}
