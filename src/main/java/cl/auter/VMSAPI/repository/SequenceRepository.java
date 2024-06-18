package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceMessageModel;
import cl.auter.VMSAPI.model.SequenceModel;

@Repository
public interface SequenceRepository extends JpaRepository<SequenceModel, Integer>{

	@Query(value = "SELECT DISTINCT ms.id_secuencia, 0 AS id_letrero, s.nombre FROM mensaje_secuencia ms INNER JOIN secuencias s ON ms.id_secuencia = s.id_secuencia WHERE ms.id_mensaje = ?1 ORDER BY s.nombre ASC", nativeQuery = true)
	public List<SequenceModel> findByIdMessage(Integer message_id);

}
