package cl.auter.VMSAPI;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{
	UsersEntity findByUsername(String nombre);
}
