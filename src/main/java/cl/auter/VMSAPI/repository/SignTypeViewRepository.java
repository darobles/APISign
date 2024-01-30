package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.view.SignTypeViewModel;

@Repository
public interface SignTypeViewRepository extends JpaRepository<SignTypeViewModel, Integer>{

}
