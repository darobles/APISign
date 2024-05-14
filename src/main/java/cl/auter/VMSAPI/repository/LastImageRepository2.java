package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.LastImageEntity2;

@Repository
public interface LastImageRepository2 extends JpaRepository<LastImageEntity2, Integer>  {

	@Query(value = "SELECT * FROM public.last_vms_images_2 WHERE id_vms = ?1 ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
	LastImageEntity2 findLastByVMS(Integer sign_id);

	LastImageEntity2 findByIdVMS(Integer idVMS);
}
