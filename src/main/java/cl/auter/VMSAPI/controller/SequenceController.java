package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.SequenceModel;
import cl.auter.VMSAPI.model.view.SequenceMessageView;
import cl.auter.VMSAPI.service.SequenceMessageViewService;
import cl.auter.VMSAPI.service.SequenceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sequence")
public class SequenceController {
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
	private SequenceMessageViewService smService;

	@GetMapping("")
	public List<SequenceModel> findAll(){
		List<SequenceModel> sequences = sequenceService.findAll();
		return sequences;	
	}
	
	@PostMapping("")
	public SequenceModel addSequence(@PathVariable("id") Integer id){
		SequenceModel sequence = sequenceService.getById(id);
		return sequence;
	}
	
	@PostMapping("/{id}")
	public SequenceModel editSequence(@PathVariable("id") Integer id,@RequestBody Map<String, Object> json){
		System.out.println(json.get("name"));
		SequenceModel sequence = sequenceService.getById(id);
		sequence.setName(String.valueOf(json.get("name")));
		sequenceService.save(sequence);
		return sequence;
	}
	
	@DeleteMapping("/{id}")
	public SequenceModel deleteSequence(@PathVariable("id") Integer id){
		SequenceModel sequence = sequenceService.getById(id);
		sequenceService.delete(sequence);
		return sequence;
	}
	
	@GetMapping("/{id}")
	public SequenceModel findById(@PathVariable("id") Integer id){
		SequenceModel sequence = sequenceService.getById(id);
		return sequence;
	}
	

	
	@GetMapping("/{id}/message")
	public List<SequenceMessageView> findMessages(@PathVariable("id") Integer id){
		List<SequenceMessageView> sequenceMessage = smService.getMessagesSequenceById(id);
		return sequenceMessage;
	}
}
