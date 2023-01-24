package service;

import console.ConsoleWriter;
import entity.Operation;
import entity.User;
import storage.InMemoryOperationStorage;
import storage.InMemoryUserStorage;

import java.util.List;
import java.util.Optional;

public class CalculatorService {
    private final InMemoryOperationStorage storage = new InMemoryOperationStorage();
    public Optional<Operation> calculate(Operation operation){
        switch(operation.getType()) {
            case 1:
                operation.setResult(sum(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return Optional.of(operation);
            case 2:
                operation.setResult(sub(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return Optional.of(operation);
            case 3:
                operation.setResult(mult(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return Optional.of(operation);
            case 4:
                operation.setResult(div(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return Optional.of(operation);


        }
        return Optional.empty();

    }

    public List<Operation> findAllByUser(User user){
        List<Operation> allByUserId = storage.getAllByUserId(user.getId());
        return allByUserId;


    }
    public void removeAll(User user){
        storage.removeAll(user.getId());

    }
    public void findByIdOperation(User user, int id){
        storage.findById(user.getId(),id).forEach(System.out::println);
    }
    private static double sum(double a, double b){
        return a+b;
    }
    private static double sub(double a, double b){
        return a-b;
    }
    private static double mult(double a, double b){
        return a*b;
    }
    private static double div(double a, double b){
        return a/b;
    }
}
