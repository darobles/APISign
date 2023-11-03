package cl.auter.VMSAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/maintenances/status")
public class MantStatusController {
	
	private final MantStatusRepository mantStatusRepository;
	
	@Autowired
	public MantStatusController(MantStatusRepository msRepository) {
		this.mantStatusRepository = msRepository;
	}
	
	@GetMapping("")
	public List<MantStatusEntity> findAll(){
		return mantStatusRepository.findAll();
	}
}
