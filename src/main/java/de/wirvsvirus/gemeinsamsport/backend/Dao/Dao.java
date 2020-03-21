package de.wirvsvirus.gemeinsamsport.backend.Dao;

import de.wirvsvirus.gemeinsamsport.backend.Entity.AbstractEntity;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public abstract class Dao<E extends AbstractEntity> {

    private final Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public Dao() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != Dao.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        //noinspection unchecked
        this.entityClass = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    @Transactional
    public Optional<E> get(long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Transactional
    public List<E> getAll() {
        CriteriaBuilder  builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query   = builder.createQuery(entityClass);
        return entityManager.createQuery(query.select(query.from(entityClass))).getResultList();
    }

    @Transactional
    public void save(@NonNull E t) {
        entityManager.persist(t);
    }

    @Transactional
    public void delete(@NonNull E t) {
        entityManager.remove(t);
    }
}
