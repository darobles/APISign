package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.LastImageEntity;

@Repository
public interface LastImgRepository extends JpaRepository<LastImageEntity, Integer>  {
	
	@Query(value= "select id,type,image,sequence_id, date,vms_id from last_vms_images where vms_id = ?1 order by date desc LIMIT 1", nativeQuery = true)
	LastImageEntity findByVmsId(int vms_id);
}
