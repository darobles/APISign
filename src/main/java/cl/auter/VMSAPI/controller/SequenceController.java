package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.SequenceMessageModel;
import cl.auter.VMSAPI.model.SequenceModel;
import cl.auter.VMSAPI.model.VMSResponseEntity;
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
    public ResponseEntity<SequenceModel> newSequence(@RequestBody SequenceModel sequence) {
    	sequenceService.save(sequence);
    	sequenceService.flush();
        return new ResponseEntity<SequenceModel>(sequence, HttpStatus.OK);
    }
	
	@PutMapping("")
	public ResponseEntity<SequenceModel> editSequence(@RequestBody SequenceModel sequence){
		System.out.println(sequence.toString());
		sequenceService.save(sequence);
		return new ResponseEntity<SequenceModel>(sequence, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<VMSResponseEntity> deleteSequence(@PathVariable("id") Integer id){
		SequenceModel sequence = sequenceService.getById(id);
		sequenceService.delete(sequence);
		VMSResponseEntity response = new VMSResponseEntity();
		response.setMessage("ok");
		response.setStatus(200);
		return new ResponseEntity<VMSResponseEntity>(response, HttpStatus.OK);
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
	
	@PostMapping("/{id}/message")
	public List<SequenceMessageView> addMessages(@PathVariable("id") Integer id,@RequestBody SequenceMessageModel sequenceMessage){
		
		return null;
	}
	
	/*@PostMapping("/{id}")
	public SequenceModel modifySecuence(@PathVariable("id") Integer id,@RequestBody Map<String, Object> json){
		SequenceModel sequence = sequenceService.getById(id);
		sequence.setName(String.valueOf(json.get("name")));
		sequenceService.save(sequence);
		return sequence;
	}*/
}
