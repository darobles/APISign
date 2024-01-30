package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.MessageModel;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Integer>{

	/*@Query(value = "SELECT imagen_b64, ubicacion_vrt FROM mensaje_imagen WHERE id_mensaje =?1  AND ubicacion_hrz = ?2", nativeQuery = true)
	public MessageModel getMessageImageModel(int id_message, int side);*/
}
