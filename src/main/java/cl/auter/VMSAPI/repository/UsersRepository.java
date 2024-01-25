package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.auter.VMSAPI.model.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{
	UsersEntity findByUsername(String nombre);
}
