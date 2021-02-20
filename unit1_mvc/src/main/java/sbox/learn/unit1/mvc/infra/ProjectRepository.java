package sbox.learn.unit1.mvc.infra;

import sbox.learn.unit1.mvc.domain.entities.Account;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ProjectRepository<TEntity,TKey> {
    List<TEntity> retreiveAll();

    void store(TEntity item);

    boolean remove(Predicate<TEntity> predicate);

    List<TEntity> find(Predicate<TEntity> predicate);

}
