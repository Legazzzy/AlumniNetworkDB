package com.example.alumninetworkcase.services;


import java.util.Collection;

public interface CrudService <T, ID> {


    /**
     * Returns an entity from the table that has an id matching parameter id.
     * <p>
     *
     * </p>
     *
     * @param   id  The id of the entity we want to find
     * @return      The entity in the database table that has an id that matches the parameter
     */
    T findById(ID id);


    /**
     * Returns a collection containing all entities stored in the table.
     * <p>
     *
     * </p>
     *
     * @return          Collection of all entities in database table
     */
    Collection<T> findAll();


    /**
     * Adds the given entity to the table.
     * <p>
     *
     * </p>
     *
     * @param   entity  The entity we want to add to the table
     * @return          The entity that has been added to the table
     */
    T add(T entity);


    /**
     * Update an entity in the table to match the parameter entity.
     * <p>
     *
     * </p>
     *
     * @param   entity  The new entity information
     * @return          The updates entity
     */
    T update(T entity);


    /**
     * Delete an entity from the table using id, if and only if, any entity with a matching id exists in the table.
     * <p>
     *
     * </p>
     *
     * @param   id  The id of the entity we want to delete
     */
    void deleteById(ID id);


    /**
     * Delete an entity from the database table, if and only if, any entity matching the parameter entity exists.
     * <p>
     *
     * </p>
     *
     * @param   entity  The entity we want to delete from the database table
     */
    void delete(T entity);

    /**
     * Check if any entity having an id matching the parameter id exists in the table.
     * <p>
     *
     * </p>
     *
     * @param   id  The id we want to check for
     * @return  True if the entity exists, false if it does not
     */
    boolean exists(ID id);
}
