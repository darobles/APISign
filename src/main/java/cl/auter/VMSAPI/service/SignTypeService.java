package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.SignTypeModel;
import cl.auter.VMSAPI.repository.SignTypeRepository;

@Service
public class SignTypeService implements SignTypeRepository{
	
	@Autowired
	SignTypeRepository signTypeRepository;

	@Override
	public List<SignTypeModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SignTypeModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SignTypeModel> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SignTypeModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return signTypeRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends SignTypeModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<SignTypeModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SignTypeModel getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignTypeModel getById(Integer id) {
		// TODO Auto-generated method stub
		return signTypeRepository.getById(id);
	}

	@Override
	public <S extends SignTypeModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SignTypeModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeModel> S save(S entity) {
		return null;
	}

	@Override
	public Optional<SignTypeModel> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SignTypeModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends SignTypeModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SignTypeModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends SignTypeModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/*public void updateSignType(Integer id_tipo_letrero, String nombre, Integer alto, Integer ancho, Integer espacio_lineas,
			Integer alto_max_simbolo, Integer id_tipo_ancho, Integer ancho_max_simbolo, Integer espaciado, Integer grano,
			Integer codificacion) {
		signTypeRepository.updateSignType(id_tipo_letrero, nombre, ancho, alto, espacio_lineas, alto_max_simbolo, id_tipo_ancho, ancho_max_simbolo, espaciado, grano, codificacion);
	}*/

	/*@Override
	public void updateSignType(Integer id_tipo_letrero, String nombre, Integer ancho, Integer alto, Integer espacio_lineas,
			Integer alto_max_simbolo, Integer id_tipo_ancho, Integer ancho_max_simbolo, Integer espaciado,
			Integer grano, Integer codificacion) {
	}*/
}
