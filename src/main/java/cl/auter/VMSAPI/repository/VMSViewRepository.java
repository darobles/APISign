package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.view.VMSViewModel;

@Repository
public interface VMSViewRepository extends JpaRepository<VMSViewModel, Integer>{

//	public VMSViewModel findVMSById(int id);
}
