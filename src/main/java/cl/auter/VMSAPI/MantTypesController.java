package cl.auter.VMSAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances/types")
public class MantTypesController {
	
	private final MantTypeRepository mantTypeRepository;
	
	@Autowired
	public MantTypesController(MantTypeRepository mtRepository) {
		this.mantTypeRepository = mtRepository;
	}
	
	@GetMapping("")
	public List<MantTypeEntity> findAll(){
		return mantTypeRepository.findAll();
	}
}
