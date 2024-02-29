package cl.auter.VMSAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.SignModel;

@Repository
public interface SignRepository extends JpaRepository<SignModel, Integer>{

}
