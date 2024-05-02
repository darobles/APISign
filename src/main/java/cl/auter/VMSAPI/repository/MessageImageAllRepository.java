package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.MessageImageAllModel;

@Repository
public interface MessageImageAllRepository extends JpaRepository<MessageImageAllModel, Integer>{

	@Query(value = "SELECT mi.id, mi.id_mensaje, mi.ubicacion_hrz, m.nombre AS nombre_mensaje, mi.imagen_b64 FROM mensaje_imagen mi INNER JOIN mensajes m ON mi.id_mensaje = m.id_mensaje ORDER BY mi.id_mensaje ASC, mi.ubicacion_hrz", nativeQuery = true)
	public List<MessageImageAllModel> getMessageImageAllModel();
	
	@Query(value = "SELECT mi.id, mi.id_mensaje, mi.ubicacion_hrz, m.nombre AS nombre_mensaje, mi.imagen_b64 FROM mensaje_imagen mi INNER JOIN mensajes m ON mi.id_mensaje = m.id_mensaje WHERE m.nombre ILIKE ?1 ORDER BY mi.id_mensaje ASC, mi.ubicacion_hrz", nativeQuery = true)
	public List<MessageImageAllModel> getMessageImageModelByName(String nombre_mensaje);
}
