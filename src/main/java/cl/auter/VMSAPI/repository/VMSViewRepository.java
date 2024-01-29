package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.VMSViewModel;

public interface VMSViewRepository extends JpaRepository<VMSViewModel, Integer>{

}
