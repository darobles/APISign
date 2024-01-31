package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import cl.auter.VMSAPI.model.view.SequenceMessageView;

public interface SequenceMessageViewRepository extends JpaRepository<SequenceMessageView
, Integer> {

}
