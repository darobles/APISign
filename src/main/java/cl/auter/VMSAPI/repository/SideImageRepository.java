package cl.auter.VMSAPI.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cl.auter.VMSAPI.model.SideImageModel;

@Repository
public interface SideImageRepository extends JpaRepository<SideImageModel, Integer>{
	
	@Query(value = "SELECT * FROM mensaje_imagen WHERE id_mensaje = ?1 AND ubicacion_hrz = ?2", nativeQuery = true)
	public SideImageModel getSideImage(int message_id, int side);
	
	@Modifying
	@Transactional
	@Query(value = "update mensaje_imagen set imagen_b64 = ?1, ubicacion_vrt = ?2 where id_mensaje = ?3 and  ubicacion_hrz = ?4", nativeQuery = true)
	public void updateImage(String image, Integer ubicacion_vrt, Integer id_mensaje, Integer ubicacion_hrz);
}
