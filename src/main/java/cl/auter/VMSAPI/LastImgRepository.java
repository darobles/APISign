package cl.auter.VMSAPI;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LastImgRepository extends JpaRepository<LastImageEntity, Integer>  {
	LastImageEntity findByVmsId(int vms_id);
}
