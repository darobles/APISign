package cl.auter.VMSAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.auter.VMSAPI.model.SignTypeModel;

@Repository
public interface SignTypeRepository extends JpaRepository<SignTypeModel, Integer>{

	/*@Transactional
	@Modifying
	@Query(value = "UPDATE tipo_letreros SET nombre = ?2, ancho = ?3, alto = ?4, espacio_lineas = ?5, alto_max_simbolo =?6, ancho_max_simbolo = ?7, id_tipo_ancho = ?8, espaciado = ?9, grano = ?10, codificacion = ?11 WHERE id_tipo_letrero = ?1")
	void updateSignType(Integer id_tipo_letrero, String nombre, Integer ancho, Integer alto, Integer espacio_lineas,
			Integer alto_max_simbolo, Integer id_tipo_ancho, Integer ancho_max_simbolo, Integer espaciado, Integer grano, Integer codificacion);*/

	/*@Query(value = "UPDATE tipo_letreros SET nombre = ?2, ancho = ?3, alto = ?4, espacio_lineas = ?5, alto_max_simbolo =?6, ancho_max_simbolo = ?7, id_tipo_ancho = ?8, espaciado = ?9, grano = ?10, codificacion = ?11 WHERE id_tipo_letrero = ?1")
	void updateSignType(Integer id_tipo_letrero, String nombre, Integer ancho, Integer alto, Integer espacio_lineas, Integer alto_max_simbolo, Integer id_tipo_ancho, Integer ancho_max_simbolo, Integer espaciado, Integer grano, Integer codificacion);*/

	/*@Query(value = "SELECT * FROM tipo_letreros WHERE id_tipo_letrero = ?1", nativeQuery = true)
	public SignTypeModel getById(Integer id);

	@Query(value = "DELETE FROM tipo_letreros WHERE id_tipo_letrero = ?1", nativeQuery = true)
	public void deleteById(Integer id);*/

}
