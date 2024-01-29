package cl.auter.VMSAPI.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.auter.VMSAPI.model.SymbolModel;

@Repository
public interface SymbolRepository extends JpaRepository<SymbolModel, Integer>{
	
	@Query(value = "SELECT * FROM simbolos  WHERE id_grupo =  ?1   AND caracter IN (?2)", nativeQuery = true)
	public List<SymbolModel> getSymbolsByGroupIdCharacteres(int group_id, String characters);
}
