package cl.auter.VMSAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cl.auter.VMSAPI.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete FROM mensajes WHERE id_tipo_letrero =?1", nativeQuery = true)
	public void deleteByType(Integer type);

}
