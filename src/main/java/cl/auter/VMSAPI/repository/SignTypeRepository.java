package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SignTypeModel;

@Repository
public interface SignTypeRepository extends JpaRepository<SignTypeModel, Integer>{

	/*@Query(value = "SELECT * FROM tipo_letreros WHERE id_tipo_letrero = ?1", nativeQuery = true)
	public SignTypeModel getById(Integer id);

	@Query(value = "DELETE FROM tipo_letreros WHERE id_tipo_letrero = ?1", nativeQuery = true)
	public void deleteById(Integer id);*/

}
