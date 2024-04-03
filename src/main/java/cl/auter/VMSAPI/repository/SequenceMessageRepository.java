package cl.auter.VMSAPI.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceMessageModel;

@Repository
public interface SequenceMessageRepository extends JpaRepository<SequenceMessageModel, Integer>{
	
	@Query(value = "select * from mensaje_secuencia where id_mensaje = ?1", nativeQuery = true)
	public List<SequenceMessageModel> findByIdMessage(Integer message_id);
	
	@Query(value = "select * from mensaje_secuencia where id_secuencia = ?1", nativeQuery = true)
	public List<SequenceMessageModel> findSeqAllById(Integer sequence_id);

	@Modifying
	@Transactional
	@Query(value = "update mensaje_secuencia set indice = ?4 where id_secuencia = ?1 and id_mensaje = ?2 and indice = ?3", nativeQuery = true)
	public void changeIndex(Integer idSequence, Integer idMessage, Integer oldIndex, Integer newIndex);
	
	
}
