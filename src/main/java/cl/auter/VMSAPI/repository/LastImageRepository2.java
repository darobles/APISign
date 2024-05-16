package cl.auter.VMSAPI.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.LastImageEntity2;

@Repository
public interface LastImageRepository2 extends JpaRepository<LastImageEntity2, Integer>  {

	@Query(value = "SELECT * FROM public.last_vms_images_2 WHERE id_vms = ?1 ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
	LastImageEntity2 findLastByVMS(Integer sign_id);

	@Query(value = "SELECT * FROM public.last_vms_images_2 WHERE id_vms = ?1 AND id_sequence = ?2 AND date_time = ?3 ORDER BY index_sequence ASC", nativeQuery = true)
	List<LastImageEntity2> findLastSequenceByVMS(Integer sign_id, Integer sequence_id, LocalDateTime date_time);

	@Query(value = "SELECT * FROM public.last_vms_images_2 WHERE id_vms = ?1 ORDER BY date_time DESC", nativeQuery = true)
	List<LastImageEntity2> findAllLastByVMS(Integer sign_id);
}
