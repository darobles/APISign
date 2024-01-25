package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.LastImageEntity;

public interface LastImgRepository extends JpaRepository<LastImageEntity, Integer>  {
	LastImageEntity findByVmsId(int vms_id);
}
