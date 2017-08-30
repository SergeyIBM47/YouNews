package com.template.service;

import com.template.domain.repository.RootRepository;
import com.template.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 4:37 PM
 * Project: Spring MVC
 */
@Transactional
public interface RootService<T> {

    RootRepository<T, Long> getRepository();

    Class<T> getEntityClass();

    /**
     * Get all entities by home id and optionally sorted with sort clause
     *
     * @param sort - sort clause, may be null
     * @return List
     */
    List<T> findAll(Sort sort);

    /**
     * Get pageable list of list model for target entity by home id
     * if it is needed
     *
     * @param paging
     * @return Page
     */
    Page<T> findAllPageable(Pageable paging);

    /**
     * Get entity by entity id
     *
     * @param id
     * @return
     */
    T get(Long id);

    /**
     * Update existing entity
     *
     * @param entity
     * @return
     */
    T update(T entity) throws NotFoundException;

    /**
     * Create new entity
     *
     * @param entity
     * @return
     */
    T insert(T entity);

    /**
     * Delete entity by entity id
     *
     * @param id - id of entity to delete
     * @return boolean
     */
    Boolean delete(Long id) throws NotFoundException;

}
