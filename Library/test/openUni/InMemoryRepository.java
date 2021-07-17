package openUni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import openUni.Persistence.IRepository;
import openUni.Persistence.PersistedObject;

public class InMemoryRepository<T extends PersistedObject> implements IRepository<T> {
    private final HashMap<Integer, T> _records;
    private Integer _identifier;
    
    public InMemoryRepository(){
        _records = new HashMap<>();
        _identifier = 0;
    }
    
    @Override
    public int save(T entity) {
        _identifier ++;
        _records.put(_identifier, entity);
        entity.setId(_identifier);
        
        return _identifier;
    }
    
    public HashMap<Integer, T> getRecords(){
        return _records;
    }
    
    public int getIdentifier(){
        return _identifier;
    }

    @Override
    public T get(int identifier) {
        return _records.get(identifier);
    }

    @Override
    public List<T> filter(Function<T, Boolean> filter) {
        List<T> recordsToReturn = new ArrayList<>();
        if(_records.isEmpty())
            return recordsToReturn;
        
        
        _records.entrySet()
                .stream()
                .map((entry) -> entry.getValue())
                .filter((value) -> filter.apply(value))
                .forEachOrdered((value) -> { recordsToReturn.add(value); });
        
        return recordsToReturn;
    }

    @Override
    public void update(T model) {
        _records.remove(model.getId());
        _records.put(model.getId(), model);
    }
}
