package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.VmsCredentialsEntity;

public interface VmsCredentialsRepository extends JpaRepository<VmsCredentialsEntity, Integer>{
	VmsCredentialsEntity findByVmsId(int vms_id);
}
