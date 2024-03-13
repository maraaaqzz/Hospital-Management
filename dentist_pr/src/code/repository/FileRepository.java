package code.repository;

import code.domain.Identifiable;
import code.exceptions.DuplicateItemException;
import code.exceptions.ItemNotFound;

public abstract class FileRepository<ID, T extends Identifiable<ID>> extends MemoryRepo<ID, T> {
    protected String filename;
    public FileRepository(String filename){
        this.filename = filename;
        //readFromFile();
    }
    protected abstract void readFromFile();
    protected abstract void writeToFile();
    @Override
    public void addItem(ID id, T elem)throws DuplicateItemException {
        super.addItem(id, elem);
        writeToFile();
    }
    @Override
    public void removeItem(ID id)throws ItemNotFound {
        super.removeItem(id);
        writeToFile();
    }
    @Override
    public void updateItem(ID id, T newItem) throws ItemNotFound{
        super.updateItem(id, newItem);
        writeToFile();
    }
}
