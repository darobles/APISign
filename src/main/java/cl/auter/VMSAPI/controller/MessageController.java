package cl.auter.VMSAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.MessageModel;
import cl.auter.VMSAPI.service.MessageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/message")
public class MessageController {
	@Autowired
	MessageService messageService;

	@GetMapping("")
	public List<MessageModel> findAll(){
		List<MessageModel> messageList = messageService.findAll();
		return messageList;		
		
	}
	
	@GetMapping("/{id}")
	public MessageModel findById(@PathVariable int id){
		MessageModel message = messageService.getById(id);
		System.out.println(message.toString());
		return message;		
		
	}
}
