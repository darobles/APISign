package cl.auter.VMSAPI.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.VMSAPI.model.CameraTypeModel;
import cl.auter.util.Constants;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/cameraType")
public class CamaraTypeController {

	@GetMapping("")
	public List<CameraTypeModel> findAll(){
            int id = 0;
            List<CameraTypeModel> cameraTypeList = new ArrayList<>();
            for (String name : Constants.CAM_TYPE) {
            	CameraTypeModel cameraType = new CameraTypeModel();
            	cameraType.setNombre(name);
            	cameraType.setId(++id);
            	cameraTypeList.add(cameraType);
            }

        
        return cameraTypeList;
		
		
	}
}
