package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import cl.auter.VMSAPI.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Integer>{

}
