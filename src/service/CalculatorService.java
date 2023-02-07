package service;

import console.ConsoleWriter;
import entity.Operation;
import entity.User;
import storage.InMemoryOperationStorage;
import storage.InMemoryUserStorage;
import storage.JDBCOperationStorage;

import java.util.List;
import java.util.Optional;

public class CalculatorService {

    private final JDBCOperationStorage jdbcStorage = new JDBCOperationStorage();

    public Optional<Operation> calculate(Operation operation){
        switch(operation.getType()) {
            case "SUM":
                operation.setResult(sum(operation.getNum1(), operation.getNum2()));
                jdbcStorage.save(operation);
                return Optional.of(operation);
            case "SUB":
                operation.setResult(sub(operation.getNum1(), operation.getNum2()));
                jdbcStorage.save(operation);
                return Optional.of(operation);
            case "MUL":
                operation.setResult(mult(operation.getNum1(), operation.getNum2()));
                jdbcStorage.save(operation);
                return Optional.of(operation);
            case "DIV":
                operation.setResult(div(operation.getNum1(), operation.getNum2()));
                jdbcStorage.save(operation);
                return Optional.of(operation);


        }
        return Optional.empty();

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
