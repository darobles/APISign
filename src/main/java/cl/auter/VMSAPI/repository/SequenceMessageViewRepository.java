package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cl.auter.VMSAPI.model.view.SequenceMessageView;

public interface SequenceMessageViewRepository extends JpaRepository<SequenceMessageView, Integer> {

	@Query(value = "SELECT * FROM sequence_message_view WHERE id_secuencia =?1 ", nativeQuery = true)
	public List<SequenceMessageView> getMessagesSequenceById(int sequence_id);
}
