package storage;

import entity.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOperationStorage {
    public void save(Operation operation) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "vandal")) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into operations values(default,?,?,?,?)");
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setDouble(2, operation.getNum2());
            preparedStatement.setString(3, operation.getType());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operation> findAll() {
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres"
                        , "postgres", "vandal")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from operations");
            List<Operation> operations = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                double num1 = resultSet.getDouble(2);
                double num2 = resultSet.getDouble(3);
                String type = resultSet.getString(4);
                double result = resultSet.getDouble(5);
                Operation operation = new Operation(id, num1, num2, result, type);
                operations.add(operation);
            }
            return operations;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public Optional<Operation> findById(int id) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres"
                        , "postgres", "vandal")) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from operations where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            double num1 = resultSet.getDouble(2);
            double num2 = resultSet.getDouble(3);
            String type = resultSet.getString(4);
            double result = resultSet.getDouble(5);
            Operation operation = new Operation(id, num1, num2, result, type);
            return Optional.of(operation);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public void deleteById(int id) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres"
                        , "postgres", "vandal")) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from operations where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {

        }
    }
}


