package cl.auter.VMSAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.auter.VMSAPI.model.MessageImageAllModel;
import cl.auter.VMSAPI.model.SideImage;
import cl.auter.VMSAPI.repository.MessageImageAllRepository;

@Service
public class MessageImageAllService implements MessageImageAllRepository{
	@Autowired
	MessageImageAllRepository messageImageAllRepository;

	@Override
	public List<MessageImageAllModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageImageAllModel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageImageAllModel> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MessageImageAllModel> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<MessageImageAllModel> entities) {
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
	public MessageImageAllModel getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageImageAllModel getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MessageImageAllModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MessageImageAllModel> findById(Integer id) {
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
	public void delete(MessageImageAllModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends MessageImageAllModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MessageImageAllModel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MessageImageAllModel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends MessageImageAllModel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	// ------------------------------------------------------------------------
	
	// JPérez 2024.04.30
	@Override
	public List<MessageImageAllModel> getMessageImageAllModel() {
		return messageImageAllRepository.getMessageImageAllModel();
	}

	@Override
	public List<MessageImageAllModel> getMessageImageModelByName(String nombre_mensaje) {
		return messageImageAllRepository.getMessageImageModelByName(nombre_mensaje);
	}


}
