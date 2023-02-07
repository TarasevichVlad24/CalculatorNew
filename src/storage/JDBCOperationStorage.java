package storage;

import entity.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCOperationStorage {
    public void save(Operation operation) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "vandal")) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into operations values(default,?,?,?,?)");
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setDouble(2,operation.getNum2());
            preparedStatement.setString(3,operation.getType());
            preparedStatement.setDouble(4,operation.getResult());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
