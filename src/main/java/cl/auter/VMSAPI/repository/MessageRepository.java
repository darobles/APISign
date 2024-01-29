package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer>{

}
