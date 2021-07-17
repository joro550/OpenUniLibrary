package openUni.Persistence;

import java.util.List;
import java.util.function.Function;

public interface IRepository<T extends PersistedObject> {

    /**
     * Saves the entity to the persistence store
     * @param entity the entity to save
     * @return
     */
    int save(T entity);

    /**
     * Get an entity by a unique identifier
     * @param identifier
     * @return The entity with the unique identifier
     */
    T get(int identifier);

    /**
     * Get collection of entities that match the filter
     * @param filter The filter to compare against entity information
     * @return All entities that match filter
     */
    List<T> filter(Function<T, Boolean> filter);

    /**
     * Updates the entity by unique identifier
     * @param entity The entity to save
     */
    void update(T entity);
}
