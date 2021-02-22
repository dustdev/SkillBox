package sbox.learn.unit1.mvc.app.repositories;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс обобщенного репозитория
 *
 * @param <TEntity> тип сущности
 * @param <TKey>    тип ключа
 */
public interface GenericRepository<TEntity, TKey> {
    /**
     * Получение списка всех элементов
     *
     * @return
     */
    List<TEntity> retreiveAll();

    /**
     * Сохранение элемента
     *
     * @param item
     */
    void store(TEntity item);

    /**
     * Удаление списка элементов по условию
     * результат означает успех (=нашлось и удалилось) или не успех (=не нашлось) операции
     *
     * @param predicate
     * @return
     */
    boolean remove(Predicate<TEntity> predicate);

    /**
     * Поиск списка элементов по условию
     *
     * @param predicate
     * @return
     */
    List<TEntity> find(Predicate<TEntity> predicate);

}
