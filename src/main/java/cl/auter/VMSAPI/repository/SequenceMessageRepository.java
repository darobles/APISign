package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SequenceMessageModel;

@Repository
public interface SequenceMessageRepository extends JpaRepository<SequenceMessageModel, Integer>{

}
