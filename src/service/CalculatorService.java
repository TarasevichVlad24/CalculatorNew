package service;

import entity.Operation;
import entity.User;
import storage.InMemoryOperationStorage;
import storage.InMemoryUserStorage;

import java.util.List;

public class CalculatorService {
    private final InMemoryOperationStorage storage = new InMemoryOperationStorage();
    public Operation calculate(Operation operation){
        switch(operation.getType()) {
            case "sum":
                operation.setResult(sum(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return operation;
            case "sub":
                operation.setResult(sub(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return operation;
            case "mult":
                operation.setResult(mult(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return operation;
            case "div":
                operation.setResult(div(operation.getNum1(), operation.getNum2()));
                storage.save(operation);
                return operation;
        }
        return null;

    }
//    storage.save(operation);
//        return operation;
    public List<Operation> findAllByUser(User user){
        List<Operation> allByUserId = storage.getAllByUserId(user.getId());
        return allByUserId;

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
