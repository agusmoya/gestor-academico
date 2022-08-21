package com.utnfrm.microservicios.commons.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommonService<E> {

    public Iterable<E> findAll() throws Exception;

    public Page<E> findAll(Pageable pageable) throws Exception;

    public E findById(Long ig) throws Exception;

    public E save(E entity) throws Exception;

    public E update(Long id, E entity) throws Exception;

    public void deleteById(Long id) throws Exception;

}
