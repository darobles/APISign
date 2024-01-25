package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Integer>{

}
