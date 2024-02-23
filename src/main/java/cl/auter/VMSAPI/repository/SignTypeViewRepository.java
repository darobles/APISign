package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.view.SignTypeViewModel;

@Repository
public interface SignTypeViewRepository extends JpaRepository<SignTypeViewModel, Integer>{

	@Query(value = "SELECT * from signtype_view where id_tipo_letrero = ?1", nativeQuery = true)
	public List<SignTypeViewModel> findAllBySignTypeId(Integer id);
	
}
