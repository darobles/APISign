package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.SequenceMessageModel;
import cl.auter.VMSAPI.repository.SequenceMessageRepository;

@Service
public class SequenceMessageService implements SequenceMessageRepository {
	
	@Autowired
	private SequenceMessageRepository sRepository;

	@Override
	public List<SequenceMessageModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SequenceMessageModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SequenceMessageModel> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SequenceMessageModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SequenceMessageModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return sRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends SequenceMessageModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<SequenceMessageModel> entities) {
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
	public SequenceMessageModel getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SequenceMessageModel getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SequenceMessageModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SequenceMessageModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SequenceMessageModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SequenceMessageModel> S save(S entity) {
		// TODO Auto-generated method stub
		return sRepository.save(entity);
	}

	@Override
	public Optional<SequenceMessageModel> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
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
		sRepository.deleteById(id);
	}

	@Override
	public void delete(SequenceMessageModel entity) {
		// TODO Auto-generated method stub
		sRepository.delete(entity);
	}
	
	
	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends SequenceMessageModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends SequenceMessageModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends SequenceMessageModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SequenceMessageModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends SequenceMessageModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	// JPérez 2024.04.02
	@Override
	public void changeIndex(Integer idSequence, Integer idMessage, Integer oldIndex, Integer newIndex) {
		sRepository.changeIndex(idSequence, idMessage, oldIndex, newIndex);
	}

}
