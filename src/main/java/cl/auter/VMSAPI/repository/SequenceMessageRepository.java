package cl.auter.VMSAPI.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceMessageEntity;

@Repository
public interface SequenceMessageRepository extends JpaRepository<SequenceMessageEntity, Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE mensaje_secuencia SET indice = ?4 WHERE id_secuencia = ?1 AND id_mensaje = ?2 AND indice = ?3", nativeQuery = true)
	public void changeIndex(Integer idSequence, Integer idMessage, Integer oldIndex, Integer newIndex);
	
}
