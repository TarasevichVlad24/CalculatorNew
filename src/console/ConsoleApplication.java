package console;

import entity.Operation;
import entity.User;
import service.CalculatorService;
import service.UserService;

import java.util.List;
import java.util.Optional;

import static console.ConsoleReader.*;

import static console.ConsoleWriter.*;

public class ConsoleApplication {
    public static ConsoleSession consoleSession;
    private final UserService userService = new UserService();
    private final CalculatorService calculatorService = new CalculatorService();

    public void run() {
        while (true) {
            if (consoleSession == null) {
                write("Hello Guest");
                write("1 - Registration, 2 - Autorization, 3 - Exit");
                int i = readInt();
                switch (i) {
                    case 1:
                        write("Enter name");
                        String name = readString();
                        write("Enter username");
                        String username = readString();
                        write("Enter password");
                        String pass = readString();
                        User user = new User(username, pass, name);
                        userService.create(user);
                        continue;
                    case 2:
                        write("Enter username");
                        String username1 = readString();
                        write("Enter password");
                        String pass1 = readString();
                        Optional<User> byUserName = userService.findByUserName(username1);
                        if (byUserName.isPresent()) {
                            User user1 = byUserName.get();
                            if (user1.getPassword().equals(pass1)) {
                                consoleSession = new ConsoleSession(user1);
                                continue;

                            } else {
                                write("Wrong password");
                                continue;
                            }
                        } else {
                            write("User not found!");
                            continue;
                        }
                    case 3:
                        return;
                }
            } else {
                write("Hello " + consoleSession.getCurrentUser().getName());
                write("1 - Calculator, 2 - History, 3 - Logout, 4 - Exit");
                switch (readInt()){
                    case 1:
                        write("Enter num1");
                        double num1 = readDouble();
                        write("Enter num2");
                        double num2 = readDouble();
                        write("Enter operation type");
                        String type = readString();
                        Operation operation = new Operation(num1,num2,type,consoleSession.getCurrentUser());
                        Operation result = calculatorService.calculate(operation);
                        write("Result = " + result.getResult());
                        continue;
                    case 2:
                        List<Operation> allByUser = calculatorService.findAllByUser(consoleSession.getCurrentUser());
                        for(Operation operation1: allByUser){
                            write(operation1.toString());

                        }
                        continue;
                    case 3:
                        consoleSession = null;
                        continue;
                    case 4:
                        return;
                }
            }
        }
    }
}





