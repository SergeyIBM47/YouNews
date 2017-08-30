package com.template.service.impl;


import com.template.domain.IdModel;
import com.template.exception.NotFoundException;
import com.template.service.RootService;
import com.template.utils.MessageResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.List;

public abstract class RootServiceImpl<T extends IdModel> extends MessageResolver implements RootService<T> {

//    @Autowired
//    protected SecurityUtils securityUtils;

    @Override
    public T get(Long id) {
        Assert.notNull(id, this.getClass().getName() + " ID is required");

        T result = null;

        beforeGet(result);
        result = getRepository().findOne(id);
        afterGet(result);

        return result;
    }

    @Override
    public Boolean delete(Long id) {
        Assert.notNull(id, this.getClass().getName() + " ID is required");

        T entity = getRepository().findOne(id);

        if (entity == null) {
            return false;
        }

        beforeDelete(entity);
        getRepository().delete(entity);
        afterDelete(entity);

        return true;
    }

    @Override
    public T insert(T input) {

        getRepository().save(input);
        beforeInsert(input);
        afterInsert(input);

        return input;
    }

    @Override
    public T update(T input) {
        Assert.notNull(input);

        T oldEntity = getRepository().findOne(input.getId());
        if (oldEntity == null) {
            throw new NotFoundException(getEntityClass());
        }

        beforeUpdate(oldEntity, input);
        getRepository().save(input);
        afterUpdate(oldEntity, input);

        return input;
    }

    /**
     * Called after get.
     * There is a special id=0 to instantiate a new entity.
     * Services that extend BaseService may override afterGet to populate new entity with defaults
     *
     * @param entity - entity
     */
    protected void afterGet(T entity) {

    }

    /**
     * Called before get.
     * Services that extend BaseService may override afterGet to populate new entity with defaults
     *
     * @param entity - entity
     */
    protected void beforeGet(T entity) {
        //do nothing here
    }

    /**
     * Called before entity integrity will get checked and before entity save for insert operations.
     *
     * @param newEntity - entity to be inserted, not null
     */
    protected void beforeInsert(T newEntity) {
        //do nothing
    }

    /**
     * Called after entity has been saved in db for insert operations
     *
     * @param entity - entity inserted, not null
     */
    protected void afterInsert(T entity) {
        //do nothing
    }

    /**
     * Called before entity integrity will get checked and before entity save for update operations
     * <p>
     * Primary use case of this method is to setup members in newEntity because some of them may not be
     * transferred in DTO due to security reasons.
     * <p>
     * Should be obligatory overridden in services for domain classes which use @JsonIgnore annotations
     * for their members
     *
     * @param oldEntity - the old entity, not null
     * @param newEntity - the new entity, not null
     */
    protected void beforeUpdate(T oldEntity, T newEntity) {
        //do nothing
    }

    /**
     * Called after entity has been saved in db as result of update operations
     *
     * @param oldEntity - the old entity, not null
     * @param newEntity - the new entity after save, not null
     */
    protected void afterUpdate(T oldEntity, T newEntity) {
    }

    /**
     * Called before entity deleting
     *
     * @param entity - entity to delete
     */
    protected void beforeDelete(T entity) {
        //do nothing here
    }

    /**
     * Called after entity removed from db.
     * Here should be cleaned up any resources owned by entity or dependent classes
     *
     * @param entity - deleted entity
     */
    protected void afterDelete(T entity) {
        //do nothing here
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public Page<T> findAllPageable(Pageable paging) {
        return getRepository().findAll(paging);
    }
}
