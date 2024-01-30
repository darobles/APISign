package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.view.VMSViewModel;

public interface VMSViewRepository extends JpaRepository<VMSViewModel, Integer>{

	public VMSViewModel findVMSById(int id);
}
