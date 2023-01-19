package storage;

import entity.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryOperationStorage {
    private long incId =1;
    private final List<Operation> operationList = new ArrayList<>();
    public void save(Operation operation){
        operation.setId(incId++);
        operationList.add(operation);
    }
    public List<Operation> findAll(){
        return new ArrayList<>(operationList);
    }
    public List<Operation> getAllByUserId(long id){
        List<Operation> collect = operationList.stream()
                .filter(operation -> operation.getOwner().getId()==id)
                .collect(Collectors.toList());
        return collect;
    }
}

