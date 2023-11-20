package cl.auter.VMSAPI;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VmsCredentialsRepository extends JpaRepository<VmsCredentialsEntity, Integer>{
	VmsCredentialsEntity findByVmsId(int vms_id);
}
