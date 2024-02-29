package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.LetreroComModel;

@Repository
public interface SignComRepository extends JpaRepository<LetreroComModel, Integer>{

}
