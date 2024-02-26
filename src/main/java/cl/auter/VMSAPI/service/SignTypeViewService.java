package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.repository.SignTypeViewRepository;

@Service
public class SignTypeViewService implements SignTypeViewRepository{
	
	@Autowired
	SignTypeViewRepository signTypeViewRepository;
	
	@Override
	public List<SignTypeViewModel> findAll() {
		// TODO Auto-generated method stub
		return signTypeViewRepository.findAll();
	}

	@Override
	public List<SignTypeViewModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SignTypeViewModel> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SignTypeViewModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<SignTypeViewModel> entities) {
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
	public SignTypeViewModel getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignTypeViewModel getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SignTypeViewModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SignTypeViewModel> findById(Integer id) {
		// TODO Auto-generated method stub
		return signTypeViewRepository.findById(id);
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
	public void delete(SignTypeViewModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends SignTypeViewModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SignTypeViewModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends SignTypeViewModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SignTypeViewModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends SignTypeViewModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	// JPérez 2024.02.22
	@Override
	public List<SignTypeViewModel> findAllBySignTypeId(Integer id) {
		// TODO Auto-generated method stub
		return signTypeViewRepository.findAllBySignTypeId(id);
	}
	

}
