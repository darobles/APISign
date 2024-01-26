package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.UsersEntity;
import cl.auter.VMSAPI.repository.UsersRepository;

@Service
public class UsersService implements UsersRepository{
	
	@Autowired
	private UsersRepository uRepository;
	
	@Override
	public List<UsersEntity> findAll() {
		// TODO Auto-generated method stub
		return uRepository.findAll();
	}

	@Override
	public List<UsersEntity> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return uRepository.findAll(sort);
	}

	@Override
	public List<UsersEntity> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return uRepository.findAllById(ids);
	}

	@Override
	public <S extends UsersEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return uRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		uRepository.flush();
	}

	@Override
	public <S extends UsersEntity> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return uRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends UsersEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return uRepository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<UsersEntity> entities) {
		// TODO Auto-generated method stub
		uRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		uRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		uRepository.deleteAllInBatch();
	}

	@Override
	public UsersEntity getOne(Integer id) {
		// TODO Auto-generated method stub
		return uRepository.getOne(id);
	}

	@Override
	public UsersEntity getById(Integer id) {
		// TODO Auto-generated method stub
		return uRepository.getById(id);
	}

	@Override
	public <S extends UsersEntity> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return uRepository.findAll(example);
	}

	@Override
	public <S extends UsersEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return uRepository.findAll(example,sort);
	}

	@Override
	public Page<UsersEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return uRepository.findAll(pageable);
	}

	@Override
	public <S extends UsersEntity> S save(S entity) {
		// TODO Auto-generated method stub
		return uRepository.save(entity);
	}

	@Override
	public Optional<UsersEntity> findById(Integer id) {
		// TODO Auto-generated method stub
		return uRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return uRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return uRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		uRepository.deleteById(id);
	}

	@Override
	public void delete(UsersEntity entity) {
		// TODO Auto-generated method stub
		uRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		uRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends UsersEntity> entities) {
		// TODO Auto-generated method stub
		uRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		uRepository.deleteAll();
	}

	@Override
	public <S extends UsersEntity> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return uRepository.findOne(example);
	}

	@Override
	public <S extends UsersEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return uRepository.findAll(example, pageable);
	}

	@Override
	public <S extends UsersEntity> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return uRepository.count(example);
	}

	@Override
	public <S extends UsersEntity> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return uRepository.exists(example);
	}

	@Override
	public UsersEntity findByUsername(String nombre) {
		// TODO Auto-generated method stub
		return uRepository.findByUsername(nombre);
	}

}
