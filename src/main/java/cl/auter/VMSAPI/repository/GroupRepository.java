package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.GrupoModel;
import cl.auter.VMSAPI.model.MessageImageModel;

@Repository
public interface GroupRepository extends JpaRepository<GrupoModel, Integer>{
	

}
