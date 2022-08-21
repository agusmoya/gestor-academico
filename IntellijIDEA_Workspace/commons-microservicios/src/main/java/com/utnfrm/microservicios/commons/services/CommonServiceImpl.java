package com.utnfrm.microservicios.commons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CommonServiceImpl<E, R extends JpaRepository<E, Long>> implements CommonService<E> {

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            return repository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) throws Exception {
        try {
            Optional<E> optionalEntity = repository.findById(id);
            return optionalEntity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(Long id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = repository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = repository.save(entity);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws Exception {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                System.out.println("3A) PASE POR AQUI xq encontré el ID");
            } else {
                System.out.println("3B) PASE POR AQUI xq NO encontré el ID");
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
