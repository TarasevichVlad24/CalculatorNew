package console;

public final class ConsoleWriter {
    public static void write(String message){
        System.out.println(message);
    }
    public static void writeErr(String message){
        System.err.println(message);
    }
}
