package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
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
import cl.auter.VMSAPI.service.SequenceMessageService;
import cl.auter.VMSAPI.service.SequenceMessageViewService;
import cl.auter.VMSAPI.service.SequenceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sequence")
public class SequenceController {
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
	SequenceMessageViewService smService;
	
	@Autowired
	SequenceMessageService sequenceMessageService;
	
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
	public ResponseEntity<SequenceMessageModel> addMessages(@PathVariable("id") Integer id,@RequestBody SequenceMessageModel sequenceMessage){
		sequenceMessageService.saveAndFlush(sequenceMessage);
		return new ResponseEntity<SequenceMessageModel>(sequenceMessage, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/message")
	public ResponseEntity<SequenceMessageModel> editMessageSequence(@RequestBody SequenceMessageModel sequenceMessage){
		sequenceMessageService.save(sequenceMessage);
		return new ResponseEntity<SequenceMessageModel>(sequenceMessage, HttpStatus.OK);
	}
	
	@DeleteMapping("/message/{id}")
	public ResponseEntity<VMSResponseEntity> deleteMessagesFromSequence(@PathVariable("id") Integer id){
		SequenceMessageModel seq = new SequenceMessageModel();
		VMSResponseEntity response =  new VMSResponseEntity();
		seq.setId(id);
		sequenceMessageService.delete(seq);
		response.setStatus(200);
		response.setMessage("ok");
		return new ResponseEntity<VMSResponseEntity>(response, HttpStatus.OK);
	}
	

	//@PostMapping("/{id}/up/{index}")
	@PostMapping("/up")
    public ResponseEntity<VMSResponseEntity> sequenceUp(@RequestBody SequenceMessageModel sequenceMessage) { //@PathVariable("id") Integer id, @PathVariable("index") Integer index) {
		int                        id               = sequenceMessage.getSequence_id();
		int                        index            = sequenceMessage.getIndex();
		List<SequenceMessageModel> sequenceMessages = sequenceMessageService.findSeqAllById(id);
		boolean                    changed          = false;
		int                        position         = -1;
		int                        i                =  0;
		
		for (SequenceMessageModel message : sequenceMessages) {
			if (message.getIndex() == index) {
				position = i;
				break;
			} 
			i ++;
		}

		if ((position >= 1) && (position < sequenceMessages.size())) {
			SequenceMessageModel message      = sequenceMessages.get(position);
			SequenceMessageModel otherMessage = sequenceMessages.get(position - 1);
			
			int otherIndex = otherMessage.getIndex();
			message.setIndex(otherIndex);
			otherMessage.setIndex(index);
			
			Random random = new Random();
			int tempIndex = -(10000 + random.nextInt(90000));  // Unique index in case someone else is modifying at the same time
			sequenceMessageService.changeIndex(id, message.getId(), index, tempIndex);
			sequenceMessageService.changeIndex(id, otherMessage.getId(), otherIndex, index);
			sequenceMessageService.changeIndex(id, message.getId(), tempIndex, otherIndex);
			
			changed = true;  // For return JSON
			index   = otherIndex;
		}

		VMSResponseEntity response =  new VMSResponseEntity();
		response.setMessage(changed ? "changed" : "not changed");
		response.setStatus(index);
		return new ResponseEntity<VMSResponseEntity>(response, HttpStatus.OK);
	}
	
	//@PostMapping("/{id}/down/{index}")
	@PostMapping("/down")
    public ResponseEntity<VMSResponseEntity> sequenceDown(@RequestBody SequenceMessageModel sequenceMessage) { //@PathVariable("id") Integer id, @PathVariable("index") Integer index) {
		int                        id               = sequenceMessage.getSequence_id();
		int                        index            = sequenceMessage.getIndex();
		List<SequenceMessageModel> sequenceMessages = sequenceMessageService.findSeqAllById(id);
		boolean                    changed          = false;
		int                        position         = -1;
		int                        i                =  0;
		
		for (SequenceMessageModel message : sequenceMessages) {
			if (message.getIndex() == index) {
				position = i;
				break;
			} 
			i ++;
		}

		if ((position >= 0) && (position < sequenceMessages.size() - 1)) {
			SequenceMessageModel message      = sequenceMessages.get(position);
			SequenceMessageModel otherMessage = sequenceMessages.get(position + 1);
			
			int otherIndex = otherMessage.getIndex();
			message.setIndex(otherIndex);
			otherMessage.setIndex(index);
			
			Random random = new Random();
			int tempIndex = -(10000 + random.nextInt(90000));  // Unique index in case someone else is modifying at the same time
			sequenceMessageService.changeIndex(id, message.getId(), index, tempIndex);
			sequenceMessageService.changeIndex(id, otherMessage.getId(), otherIndex, index);
			sequenceMessageService.changeIndex(id, message.getId(), tempIndex, otherIndex);
			
			changed = true;  // For return JSON
			index   = otherIndex;
		}

		VMSResponseEntity response =  new VMSResponseEntity();
		response.setMessage(changed ? "changed" : "not changed");
		response.setStatus(index);
		return new ResponseEntity<VMSResponseEntity>(response, HttpStatus.OK);
	}
	
	/*@PostMapping("/{id}")
	public SequenceModel modifySecuence(@PathVariable("id") Integer id,@RequestBody Map<String, Object> json){
		SequenceModel sequence = sequenceService.getById(id);
		sequence.setName(String.valueOf(json.get("name")));
		sequenceService.save(sequence);
		return sequence;
	}*/
	
	/*@PostMapping("/{id}/send")
	public ResponseEntity<VMSResponseEntity> sendSequence(@PathVariable("id") Integer id){
		VMSResponseEntity response = new VMSResponseEntity();
		
		
		
		response.setMessage("ok");
		response.setStatus(200);
		return new ResponseEntity<VMSResponseEntity>(response,HttpStatus.OK);
	}*/
}
