package console;

import entity.Operation;
import entity.User;
import service.CalculatorService;
import service.UserService;

import java.util.List;
import java.util.Optional;

import static console.ConsoleReader.*;
import static console.ConsoleWriter.write;

public class ConsoleApplication {
    public static ConsoleSession consoleSession;
    private final UserService userService = new UserService();
    private final CalculatorService calculatorService = new CalculatorService();
    private final String NAME_REGEX = "^[A-Z]{1}[a-z]{1,12}$";
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$";


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
                        if (!name.matches(NAME_REGEX)) {
                            ConsoleWriter.writeErr(name + " is invalid name");
                            continue;
                        }
                        write("Enter username");
                        String username = readString();
                        write("Enter password");
                        String pass = readString();
                        if (!pass.matches(PASSWORD_REGEX)) {
                            ConsoleWriter.writeErr(pass + " is invalid password");


                        }
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
                switch (readInt()) {
                    case 1:
                        write("Enter num1");
                        double num1 = readDouble();
                        write("Enter num2");
                        double num2 = readDouble();
                        write("1- summary, " +
                                "2 - subtract, " +
                                "3 - multiplication, " +
                                "4 - divined");
                        int type = readInt();
                        Operation operation = new Operation(num1, num2, type, consoleSession.getCurrentUser());
                        Optional<Operation> result = calculatorService.calculate(operation);
                        write("Result = " + result.toString());
                        continue;


                    case 2:
                        write("1 - CheckAllHistory, 2 - RemoveAllHistory,3 - FindById");
                        switch (readInt()) {
                            case 1:
                                List<Operation> allByUser = calculatorService.findAllByUser(consoleSession.getCurrentUser());
                                for (Operation operation1 : allByUser) {
                                    write(operation1.toString());
                                }
                                continue;
                            case 2:
                                calculatorService.removeAll(consoleSession.getCurrentUser());
                                continue;
                            case 3:
                                write("enter id");
                                calculatorService.findByIdOperation(readInt());
                                continue;
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





