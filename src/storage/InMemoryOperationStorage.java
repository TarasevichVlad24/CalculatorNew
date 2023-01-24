package storage;

import entity.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryOperationStorage {
    private int incId =1;
    private final List<Operation> operationList = new ArrayList<>();
    public void save(Operation operation){
        operation.setId(incId++);
        operationList.add(operation);
    }

    public List<Operation> getAllByUserId(int id){
        List<Operation> collect = operationList.stream()
                .filter(operation -> operation.getOwner().getId()==id)
                .collect(Collectors.toList());
        return collect;
    }
    public void removeAll(int id){
        List<Operation> allInfo = getAllByUserId(id);
        operationList.removeAll(new ArrayList<>(allInfo));

        }

    public List<Operation> findById(int id, int i){
        List<Operation> allInnfo = getAllByUserId(id);
        return allInnfo.stream()
                .filter(operation -> operation.getId()==id)
                .toList();
    }
}




