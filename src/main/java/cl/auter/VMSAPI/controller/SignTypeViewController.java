package cl.auter.VMSAPI.controller;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.NewSignModel;
import cl.auter.VMSAPI.model.SignTypeModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.service.SignTypeService;
import cl.auter.VMSAPI.service.SignTypeViewService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/signType")
public class SignTypeViewController {

	@Autowired
	SignTypeViewService signTypeViewService;
	
	@Autowired
	SignTypeService signTypeService;

	@GetMapping("")
	public List<SignTypeViewModel> findAll(){
		List<SignTypeViewModel> vmsList = signTypeViewService.findAll();
		return vmsList;
	}
	
	// JPérez 2024.05.06
	@DeleteMapping("/{idSignType}")
	public ResponseEntity<JsonObject> deleteSignType(@PathVariable("idSignType") Integer idSignType) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		HttpStatus        status = HttpStatus.NOT_MODIFIED;
		try {
			SignTypeModel st  = signTypeService.getById(idSignType);
			if (st != null) {
				try {
					signTypeService.deleteById(idSignType);
					job.add("result", "ok");
					status = HttpStatus.OK;
				} catch (Exception ex) {
					job.add("result", "Cannot be deleted");
				}
			} else {
				job.add("result", "Id doesn't exist");
			}
		} catch (Exception ex) {
			job.add("result", "Id doesn't exist");
		}
		return new ResponseEntity<JsonObject>(job.build(), status);
	}
	
	// JPérez 2024.05.07
	@PutMapping("/{idSignType}")
	public ResponseEntity<JsonObject> updateSignType(@PathVariable("idSignType") Integer idSignType, @RequestBody SignTypeModel json) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		HttpStatus        status = HttpStatus.NOT_MODIFIED;
		try {
			SignTypeModel st = signTypeService.getById(idSignType);
			if (st != null) {
				json.setId(idSignType); /////////
				if ((json.getName() == null) || (json.getName().isEmpty())) {
					json.setName(st.getName());
				}
				signTypeService.saveAndFlush(json);
				job.add("result", "ok");
				status = HttpStatus.OK;
			} else {
				job.add("result", "Id doesn't exist");
			}
		} catch (Exception ex) {
			job.add("result", "Id doesn't exist");
		}
		return new ResponseEntity<JsonObject>(job.build(), status);
	}
	
}
