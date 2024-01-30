package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.view.MessageViewModel;

@Repository
public interface MessageViewRepository extends JpaRepository<MessageViewModel, Integer>{

}
