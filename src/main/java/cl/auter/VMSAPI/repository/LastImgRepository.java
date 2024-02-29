package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.LastImageEntity;

@Repository
public interface LastImgRepository extends JpaRepository<LastImageEntity, Integer>  {
	LastImageEntity findByVmsId(int vms_id);
}
