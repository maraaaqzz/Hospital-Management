package code.repository;

import code.domain.Identifiable;

import java.util.HashMap;
import java.util.Map;

import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;

public class MemoryRepo<ID, T extends Identifiable<ID>> implements IRepository<ID, T> {

    protected Map<ID, T> items;
    public MemoryRepo(){items = new HashMap<>();}
    @Override
    public void addItem(ID id, T item) throws DuplicateItemException{
        if (items.containsKey(item.getId())) {
            throw new DuplicateItemException("An item with this id already exists!");
        }
        items.put(item.getId(), item);
    }

    @Override
    public void removeItem(ID id) throws ItemNotFound{
        if(items.containsKey(id))
            items.remove(id);
        else
            throw new ItemNotFound("Item not found!");
    }

    @Override
    public void updateItem(ID id, T newItem) throws ItemNotFound {
        if(items.containsKey(id))
        {
            items.replace(id, newItem);
        }
        else
            throw new ItemNotFound("Item not found!");
    }

    @Override
    public T findItem(ID id) throws ItemNotFound{
       if(items.containsKey(id)) return items.get(id);
       else
        throw new ItemNotFound("Item not found!");
    }

    @Override
    public Iterable<T> getALlItems() {
        return items.values();
    }
}
