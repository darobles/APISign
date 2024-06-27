package cl.auter.VMSAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.auter.VMSAPI.model.ScheduleModel;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Integer>{

	@Query(value = "SELECT * FROM schedule_vms WHERE id_vms IN ?1 AND hour = ?2 AND days & ?3 > 0 ORDER BY days ASC, hour ASC", nativeQuery = true)
	public List<ScheduleModel> findByParams(List<Integer> id_vms, String hour, Integer days);

}
