package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.LastImageEntity;
import cl.auter.VMSAPI.repository.LastImgRepository;

@Service
public class LastImgService implements LastImgRepository {
	
	@Autowired
	private LastImgRepository lRepository;

	@Override
	public List<LastImageEntity> findAll() {
		// TODO Auto-generated method stub
		return lRepository.findAll();
	}

	@Override
	public List<LastImageEntity> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return lRepository.findAll(sort);
	}

	@Override
	public List<LastImageEntity> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return lRepository.findAllById(ids);
	}

	@Override
	public <S extends LastImageEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return lRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		lRepository.flush();
	}

	@Override
	public <S extends LastImageEntity> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return lRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends LastImageEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return lRepository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<LastImageEntity> entities) {
		// TODO Auto-generated method stub
		lRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		lRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		lRepository.deleteAllInBatch();
	}

	@Override
	public LastImageEntity getOne(Integer id) {
		// TODO Auto-generated method stub
		return lRepository.getOne(id);
	}

	@Override
	public LastImageEntity getById(Integer id) {
		// TODO Auto-generated method stub
		return lRepository.getById(id);
	}

	@Override
	public <S extends LastImageEntity> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return lRepository.findAll(example);
	}

	@Override
	public <S extends LastImageEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return lRepository.findAll(example, sort);
	}

	@Override
	public Page<LastImageEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return lRepository.findAll(pageable);
	}

	@Override
	public <S extends LastImageEntity> S save(S entity) {
		// TODO Auto-generated method stub
		return lRepository.save(entity);
	}

	@Override
	public Optional<LastImageEntity> findById(Integer id) {
		// TODO Auto-generated method stub
		return lRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return lRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return lRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		lRepository.deleteById(id);
	}

	@Override
	public void delete(LastImageEntity entity) {
		// TODO Auto-generated method stub
		lRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		lRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends LastImageEntity> entities) {
		// TODO Auto-generated method stub
		lRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		lRepository.deleteAll();
	}

	@Override
	public <S extends LastImageEntity> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return lRepository.findOne(example);
	}

	@Override
	public <S extends LastImageEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return lRepository.findAll(example, pageable);
	}

	@Override
	public <S extends LastImageEntity> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return lRepository.count(example);
	}

	@Override
	public <S extends LastImageEntity> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return lRepository.exists(example);
	}

	@Override
	public LastImageEntity findByVmsId(int vms_id) {
		// TODO Auto-generated method stub
		return lRepository.findByVmsId(vms_id);
	}

}
