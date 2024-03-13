package code.repository;

import code.domain.Identifiable;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;

public interface IRepository<ID, T extends Identifiable<ID>> {

    public void addItem(ID id, T item) throws DuplicateItemException;

    public void removeItem(ID item) throws ItemNotFound;
    public void updateItem(ID id, T newItem) throws ItemNotFound;
    public T findItem(ID id) throws ItemNotFound;

    public Iterable<T> getALlItems();
}