package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.view.SignMessageViewModel;

@Repository
public interface SignMessageViewRepository extends JpaRepository<SignMessageViewModel, Integer>{

	@Query(value = "select * from sign_message_view where id_letrero = ?1", nativeQuery = true)
	public List<SignMessageViewModel> findAllBySignId(Integer id);
}
