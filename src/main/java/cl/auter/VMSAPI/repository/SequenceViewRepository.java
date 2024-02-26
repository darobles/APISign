package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceViewModel;

@Repository
public interface SequenceViewRepository extends JpaRepository<SequenceViewModel, Integer>{

	@Query(value = "SELECT id_secuencia, nombre, id_tipo_letrero, nombre_tipo_letrero FROM sequence_view WHERE id_tipo_letrero =?1", nativeQuery = true)
	public List<SequenceViewModel> findByIdVMS(int id_vms);
}
