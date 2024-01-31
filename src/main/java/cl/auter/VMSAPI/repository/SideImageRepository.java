package cl.auter.VMSAPI.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.auter.VMSAPI.model.SideImageModel;

@Repository
public interface SideImageRepository extends JpaRepository<SideImageModel, Integer>{
	
	@Query(value = "SELECT * FROM mensaje_imagen WHERE id_mensaje = ?1 AND ubicacion_hrz = ?2", nativeQuery = true)
	public SideImageModel getSideImage(int message_id, int side);
}
