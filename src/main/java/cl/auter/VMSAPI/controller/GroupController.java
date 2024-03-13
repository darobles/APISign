package cl.auter.VMSAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.GrupoModel;
import cl.auter.VMSAPI.service.GroupService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/group")
public class GroupController {

	@Autowired
	GroupService groupService;

	@GetMapping("")
	public List<GrupoModel> findAll(){
		List<GrupoModel> messageList = groupService.findAll();
		return messageList;		
		
	}
}
