package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceModel;

@Repository
public interface SequenceRepository extends JpaRepository<SequenceModel, Integer>{

}
