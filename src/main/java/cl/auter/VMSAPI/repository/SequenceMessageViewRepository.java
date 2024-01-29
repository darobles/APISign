package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.SequenceMessageEntity;

public interface SequenceMessageViewRepository extends JpaRepository<SequenceMessageEntity
, Integer> {

}
