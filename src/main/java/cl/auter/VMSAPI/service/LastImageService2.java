package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.LastImageEntity2;
import cl.auter.VMSAPI.repository.LastImageRepository2;

@Service
public class LastImageService2 implements LastImageRepository2 {

	@Autowired
	private LastImageRepository2 lastImageRepository;
	
	@Override
	public List<LastImageEntity2> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LastImageEntity2> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LastImageEntity2> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends LastImageEntity2> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return lastImageRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends LastImageEntity2> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<LastImageEntity2> entities) {
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
	public LastImageEntity2 getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LastImageEntity2 getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LastImageEntity2> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> S save(S entity) {
		// TODO Auto-generated method stub
		return lastImageRepository.save(entity);
	}

	@Override
	public Optional<LastImageEntity2> findById(Integer id) {
		// TODO Auto-generated method stub
		return lastImageRepository.findById(id);
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
		lastImageRepository.deleteById(id);
	}

	@Override
	public void delete(LastImageEntity2 entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll(Iterable<? extends LastImageEntity2> entities) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public <S extends LastImageEntity2> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends LastImageEntity2> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends LastImageEntity2> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LastImageEntity2 findByIdVMS(Integer idVMS) {
		return lastImageRepository.findByIdVMS(idVMS);
	}

	public LastImageEntity2 findLastByVMS(Integer sign_id) {
		// TODO Auto-generated method stub
		return lastImageRepository.findLastByVMS(sign_id);
	}
}
