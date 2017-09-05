package dao;

import java.util.List;

public interface GenericDAO<T extends IdentifiedTable<PK>,PK> {
    T getById(PK id);
    T add(T entity);
    T update(T entity);
    List<T> getAll();
    void delete(T entity);
}
